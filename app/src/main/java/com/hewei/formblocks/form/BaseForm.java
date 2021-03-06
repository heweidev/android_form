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

import com.hewei.formblocks.Utils;
import com.hewei.formblocks.annotations.ActionMenu;
import com.hewei.formblocks.annotations.Block;
import com.hewei.formblocks.blocks.BaseBlock;
import com.hewei.formblocks.blocks.BlockFactory;
import com.hewei.formblocks.blocks.factory.InvalidBlockFactory;
import com.hewei.formblocks.data.DataProvider;
import com.hewei.formblocks.data.ObjectProvider;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class BaseForm {
    private static final String TAG = "BaseForm";

    protected Context mContext;
    private Map<String, BaseBlock<?>> mBlocks = new HashMap<>();

    public BaseForm(Context context) {
        mContext = context;
    }

    public void onSetup(@XmlRes int xml, LinearLayout container) throws XmlPullParserException, IOException {
        XmlResourceParser xpp = mContext.getResources().getXml(xml);

        int eventType = xpp.getEventType();
        String name;
        String id;
        String type = null;
        String factory = null;
        String tempType;
        String tempFactory;
        int size;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            name = xpp.getName();
            if(eventType == XmlPullParser.START_DOCUMENT) {

            } else if(eventType == XmlPullParser.START_TAG) {
                if ("forms".equals(name)) {
                    type = xpp.getAttributeValue(null, "type");
                    factory = xpp.getAttributeValue(null, "factory");
                } else if ("item".equals(name)) {
                    id = xpp.getAttributeValue(null, "id");
                    size = xpp.getAttributeIntValue(null, "size", 1);

                    // 优先使用item的，如果没有使用form的
                    tempType = xpp.getAttributeValue(null, "type");
                    if (tempType != null) {
                        type = tempType;
                    }
                    tempFactory = xpp.getAttributeValue(null, "factory");
                    if (tempFactory != null) {
                        factory = tempFactory;
                    }

                    try {
                        Class<? extends BlockFactory<?>> clsFactory = null;
                        if (!TextUtils.isEmpty(factory)) {
                            clsFactory = (Class<? extends BlockFactory<?>>) Class.forName(factory);
                        }
                        onSetup(id, Class.forName(type), clsFactory, size, null, container);
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

                String id = block.id();
                Class<? extends BlockFactory<?>> clsFactory = block.factory();
                int size = block.size();
                Class<?> itemType;
                if (size > 1) {
                    if (!field.getType().isArray()) {
                        // ToDo invalid block type
                        continue;
                    }
                    itemType = field.getType().getComponentType();
                } else if (size  == 1) {
                    itemType = field.getType();
                } else {
                    continue;
                }

                Object object = onSetup(id, itemType, clsFactory, size, Arrays.asList(block.args()), container);
                if (object != null) {
                    try {
                        field.set(this, object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void onSetup(BlockProvider provider, LinearLayout container) {
        if (provider == null) {
            return;
        }

        onSetup(provider.getId(), provider.getItemType(), provider.getFactory(),
                provider.getSize(), provider.getArgs(), container);
    }

    public void onSetup(BatchProvider provider, LinearLayout container) {
        if (provider == null) {
            return;
        }

        List<BlockProvider> providers = provider.getProviders();
        if (providers == null || providers.size() == 0) {
            return;
        }

        for (BlockProvider p : providers) {
            onSetup(p, container);
        }
    }

    Object onSetup(String id, Class<?> itemType, Class<? extends BlockFactory<?>> clsFactory,
                   int size, List<String> args, LinearLayout container) {
        BaseBlock<?> block;
        if (size == 1) {
            try {
                block = createBlock(clsFactory, itemType);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            if (block != null) {
                if (args != null) {
                    block.setArgs(args);
                }

                View itemView = block.getView(mContext, container);
                if (itemView != null) {
                    container.addView(itemView);
                    saveBlock(id, block);
                    return block;
                }
            }
        } else if (size > 1) {
            Object itemArray = Array.newInstance(itemType, size);
            for (int i = 0; i < size; i++) {
                try {
                    block = createBlock(clsFactory, itemType);
                    if (block != null) {
                        if (args != null) {
                            block.setArgs(args);
                        }

                        View itemView = block.getView(mContext, container);
                        Array.set(itemArray, i, block);
                        if (itemView != null) {
                            container.addView(itemView);
                            saveBlock(id, block);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return itemArray;
        }

        return null;
    }

    private void saveBlock(String id, BaseBlock<?> block) {
        if (!Block.NONE_ID.equals(id)) {
            mBlocks.put(id, block);
        }
    }

    private BaseBlock<?> createBlock(Class<? extends BlockFactory<?>> factoryCls, Class<?> cls)
            throws IllegalAccessException, InstantiationException {
        if (factoryCls == null || InvalidBlockFactory.class.equals(factoryCls)) {
            return (BaseBlock<?>) cls.newInstance();
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

    /**
     * 直接初始化field，效率较高。但是无法感知数据的设置。
     * 另外，也会出现数据不兼容问题。（比如，表单数据是String类型，但是cls中对应字段是int类型）
     * 参照Json解析的思路弄一个Converter？
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getDataByFieldOnly(Class<T> cls) {
        T t;
        try {
            t = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        for (Map.Entry<String, BaseBlock<?>> entry : mBlocks.entrySet()) {
            try {
                String fieldName = entry.getKey();
                Field field = cls.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(t, entry.getValue().getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return t;
    }

    /**
     * 从表单获取数据，这里采用的是便利cls的成员，然后从表单取数据的方法。
     * 所以，如果成员中有较多非表单数据，则效率较低。
     * 反之，采用从表单获取数据然后设置到成员，会数据不兼容难过的问题。
     * （比如，表单的数据类型是指定类型的子类，这样通过反射将找不到set方法）
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getData(Class<T> cls) {
        T t;
        try {
            t = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        HashSet<String> okList = new HashSet<>();

        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            String name = Utils.tripPrefix("set", m.getName());
            BaseBlock<?> block = mBlocks.get(name);
            if (block != null) {
                m.setAccessible(true);
                try {
                    m.invoke(t, block.getData());
                    okList.add(name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 数据已满
        if (okList.size() == mBlocks.size()) {
            return t;
        }

        // 如果数据没有满，直接设置field
        for (Map.Entry<String, BaseBlock<?>> entry : mBlocks.entrySet()) {
            try {
                String fieldName = entry.getKey();
                if (okList.contains(fieldName)) {
                    continue;
                }

                Field field = cls.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(t, entry.getValue().getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return t;
    }

    public void bindData(Object data) {
        bindDataProvider(new ObjectProvider(data));
    }

    public void bindDataProvider(DataProvider provider) {
        for (Map.Entry<String, BaseBlock<?>> entry : mBlocks.entrySet()) {
            String key = entry.getKey();
            try {
                BaseBlock baseBlock = entry.getValue();
                baseBlock.setData(provider.getData(key));
            } catch (Exception e) {
                // type 不兼容
                e.printStackTrace();
            }
        }
    }
}
