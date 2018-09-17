package com.hewei.formblocks.data;

import com.hewei.formblocks.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class ObjectProvider implements DataProvider {
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
            Method method = cls.getMethod(Utils.field2Method("get", key));
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
