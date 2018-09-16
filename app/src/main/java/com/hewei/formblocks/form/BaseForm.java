package com.hewei.formblocks.form;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.annotation.XmlRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hewei.formblocks.annotations.ActionMenu;
import com.hewei.formblocks.annotations.Block;
import com.hewei.formblocks.blocks.BaseBlock;
import com.hewei.formblocks.blocks.BlockFactory;
import com.hewei.formblocks.blocks.factory.InvalidBlockFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseForm {
    private static final String TAG = "BaseForm";

    protected Context mContext;

    private Map<String, BaseBlock> mBlocks = new HashMap<>();

    public BaseForm(Context context) {
        mContext = context;
    }

    public void onSetup(@XmlRes int xml, LinearLayout container) throws XmlPullParserException, IOException {
        XmlResourceParser xpp = mContext.getResources().getXml(xml);

        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = xpp.getName();
            if(eventType == XmlPullParser.START_DOCUMENT) {

            } else if(eventType == XmlPullParser.START_TAG) {
                if ("forms".equals(name)) {

                } else if ("item".equals(name)) {
                    String id = xpp.getAttributeValue(null, "id");
                    String type = xpp.getAttributeValue(null, "type");
                    String factory = xpp.getAttributeValue(null, "factory");
                    int size = xpp.getAttributeIntValue(null, "size", 1);

                    try {
                        Class<? extends BlockFactory<?>> clsFactory = null;
                        if (!TextUtils.isEmpty(factory)) {
                            clsFactory = (Class<? extends BlockFactory<?>>) Class.forName(factory);
                        }
                        onSetup(id, Class.forName(type), clsFactory, size, container);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if(eventType == XmlPullParser.END_TAG) {

            } else if(eventType == XmlPullParser.TEXT) {

            }
            eventType = xpp.next();
        }
    }

    public void onSetup(LinearLayout container) {
        // init block
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            Block block = field.getAnnotation(Block.class);
            if (block != null) {
                field.setAccessible(true);

                Class<? extends BlockFactory<?>> clsFactory = block.factory();
                BaseBlock baseBlock;

                int size = block.size();
                if (size == 1) {
                    try {
                        baseBlock = createBlock(clsFactory, field.getType());
                        field.set(this, baseBlock);

                        String id = block.id();
                        if (!Block.NONE_ID.equals(id)) {
                            mBlocks.put(id, baseBlock);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }

                    if (baseBlock != null) {
                        View itemView = baseBlock.getView(mContext, container);
                        container.addView(itemView);
                    }
                } else if (size > 1) {
                    if (!field.getType().isArray()) {
                        // ToDo invalid block type
                        continue;
                    }

                    Class<?> itemType = field.getType().getComponentType();
                    Object itemArray = Array.newInstance(itemType, size);

                    for (int i = 0; i < size; i++) {
                        try {
                            baseBlock = createBlock(clsFactory, itemType);
                            if (baseBlock != null) {
                                View itemView = baseBlock.getView(mContext, container);
                                Array.set(itemArray, i, baseBlock);
                                if (itemView != null) {
                                    container.addView(itemView);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        field.set(this, itemArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void onSetup(String id, Class<?> itemType, Class<? extends BlockFactory<?>> clsFactory,
                         int size, LinearLayout container) {
        BaseBlock baseBlock;
        if (size == 1) {
            try {
                baseBlock = createBlock(clsFactory, itemType);
                if (!Block.NONE_ID.equals(id)) {
                    mBlocks.put(id, baseBlock);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if (baseBlock != null) {
                View itemView = baseBlock.getView(mContext, container);
                if (itemView != null) {
                    container.addView(itemView);
                    mBlocks.put(id, baseBlock);
                }
            }
        } else if (size > 1) {
            Object itemArray = Array.newInstance(itemType, size);
            for (int i = 0; i < size; i++) {
                try {
                    baseBlock = createBlock(clsFactory, itemType);
                    if (baseBlock != null) {
                        View itemView = baseBlock.getView(mContext, container);
                        Array.set(itemArray, i, baseBlock);
                        if (itemView != null) {
                            container.addView(itemView);
                            mBlocks.put(id + "." + i, baseBlock);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private BaseBlock createBlock(Class<? extends BlockFactory<?>> factoryCls, Class<?> cls)
            throws IllegalAccessException, InstantiationException {
        if (factoryCls == null || InvalidBlockFactory.class.equals(factoryCls)) {
            return (BaseBlock) cls.newInstance();
        } else {
            return factoryCls.newInstance().create();
        }
    }

    public BaseBlock<?> getBlock(String id) {
        return mBlocks.get(id);
    }

    public void buildMenu(MenuInflater inflater, Menu menu) {
        ActionMenu menuAnnotation = getClass().getAnnotation(ActionMenu.class);
        if (menuAnnotation != null) {
            inflater.inflate(menuAnnotation.value(), menu);
        }
    }

    public void onTearDown() {

    }

    public boolean onEvent(int id) {
        Log.d(TAG, "onEvent " + id);
        return true;
    }

    public interface DataProvider {
        Object getData(String key);
    }

    /**
    public <T> T getData(Class<T> cls) {
        T t;
        try {
            t = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        for (Map.Entry<String, BaseBlock> entry : mBlocks.entrySet()) {
            try {
                String fieldName = entry.getKey();
                Object obj = entry.getValue().getData();
                Method m = cls.getMethod(field2Method("set", fieldName), obj.getClass());
                if (m != null) {
                    m.invoke(t, obj);
                } else {
                    Field field = cls.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(t, entry.getValue().getData());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return t;
    }*/

    public <T> T getData(Class<T> cls) {
        T t;
        try {
            t = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            String name = tripPrefix("set", m.getName());
            BaseBlock block = mBlocks.get(name);
            if (block != null) {
                m.setAccessible(true);
                try {
                    m.invoke(t, block.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return t;
    }

    public void bindData(Object data) {
        bindDataProvider(new ObjectProvider(data));
    }

    public void bindDataProvider(DataProvider provider) {
        for (Map.Entry<String, BaseBlock> entry : mBlocks.entrySet()) {
            String key = entry.getKey();
            try {
                entry.getValue().setData(provider.getData(key));
            } catch (Exception e) {
                // type 不兼容
                e.printStackTrace();
            }
        }
    }

    public static class ObjectProvider implements DataProvider {
        private Object mObject;

        public ObjectProvider(Object o) {
            mObject = o;
        }

        @Override
        public Object getData(String key) {
            if (mObject == null) {
                return null;
            }

            Class<?> cls = mObject.getClass();

            try {
                Method method = cls.getMethod(field2Method("get", key));
                if (method != null) {
                    return method.invoke(mObject);
                }

                Field field = cls.getField(key);
                if (field != null) {
                    return field.get(mObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private static String field2Method(String prefix, String fieldName) {
        if (TextUtils.isEmpty(fieldName)) {
            return null;
        }

        String name =  prefix + Character.toUpperCase(fieldName.charAt(0));
        if (fieldName.length() > 1) {
            return name + fieldName.substring(1);
        } else {
            return name;
        }
    }

    private static String tripPrefix(String prefix, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }

        if (TextUtils.isEmpty(prefix)) {
            return str;
        }

        int prefixLen = prefix.length();
        if (str.length() < prefixLen) {
            return null;
        }

        if (prefix.equals(str.substring(0, prefixLen))) {
            String target = str.substring(prefixLen);
            return Character.toLowerCase(target.charAt(0)) + target.substring(1);
        }

        return null;
    }
}
