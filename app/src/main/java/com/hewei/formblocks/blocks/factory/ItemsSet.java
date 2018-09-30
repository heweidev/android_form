package com.hewei.formblocks.blocks.factory;

import com.hewei.formblocks.annotation.ItemEnum;
import com.hewei.formblocks.data.ListItem;
import com.hewei.formblocks.model.EIdType;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 * 将枚举变成listitem
 * ToDo 可以用annotationProcessor替代反射
 */
public class ItemsSet {
    @ItemEnum(EIdType.class)
    public static ListItem[] ID_TYPE_ITEMS;

    public static void init() {
        Field[] fields = ItemsSet.class.getDeclaredFields();
        for (Field field : fields) {
            ItemEnum itemEnum = field.getAnnotation(ItemEnum.class);
            if (itemEnum == null) {
                continue;
            }
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            Class<?> cls = itemEnum.value();
            if (!cls.isEnum()) {
                continue;
            }

            Object itemArray = cls.getEnumConstants();
            int len = Array.getLength(itemArray);
            ListItem[] spinnerItems = new ListItem[len];
            for (int i = 0; i < len; i++) {
                Enum<?> item = (Enum<?>) Array.get(itemArray, i);
                spinnerItems[i] = (ListItem) item;
            }

            try {
                field.set(null, spinnerItems);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
