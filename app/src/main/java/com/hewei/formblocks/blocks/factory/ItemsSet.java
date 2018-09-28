package com.hewei.formblocks.blocks.factory;

import com.hewei.formblocks.annotation.ItemEnum;
import com.hewei.formblocks.blocks.SpinnerLine;
import com.hewei.formblocks.model.EIdType;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ItemsSet {
    @ItemEnum(EIdType.class)
    public static SpinnerLine.SpinnerItem[] ID_TYPE_ITEMS;

    public static void init() {
        Field[] fields = ItemsSet.class.getDeclaredFields();
        for (Field field : fields) {
            ItemEnum itemEnum = field.getAnnotation(ItemEnum.class);
            if (itemEnum == null) {
                continue;
            }

            Class<?> cls = itemEnum.value();
            if (!cls.isEnum()) {
                continue;
            }

            Object itemArray = cls.getEnumConstants();
            int len = Array.getLength(itemArray);
            SpinnerLine.SpinnerItem[] spinnerItems = new SpinnerLine.SpinnerItem[len];
            for (int i = 0; i < len; i++) {
                Enum<?> item = (Enum<?>) Array.get(itemArray, i);
                spinnerItems[i] = (SpinnerLine.SpinnerItem) item;
            }

            try {
                field.set(null, spinnerItems);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
