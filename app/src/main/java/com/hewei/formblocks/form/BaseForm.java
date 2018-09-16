package com.hewei.formblocks.form;

import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hewei.formblocks.annotations.ActionMenu;
import com.hewei.formblocks.annotations.Block;
import com.hewei.formblocks.blocks.BaseBlock;
import com.hewei.formblocks.blocks.BlockFactory;

import java.lang.reflect.Field;

public class BaseForm {
    private static final String TAG = "BaseForm";

    protected Context mContext;

    public BaseForm(Context context) {
        mContext = context;
    }

    public void onSetup(LinearLayout container) {
        // init block
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            Block block = field.getAnnotation(Block.class);
            if (block != null) {
                field.setAccessible(true);

                Class<?> clsFactory = block.factory();
                BaseBlock baseBlock;

                try {
                    if (Object.class.equals(clsFactory)) {
                        baseBlock = (BaseBlock) field.getType().newInstance();
                    } else {
                        baseBlock = ((BlockFactory) clsFactory.newInstance()).create();
                    }
                    field.set(this, baseBlock);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                if (baseBlock != null) {
                    View itemView = baseBlock.getView(mContext, container);
                    container.addView(itemView);
                }
            }
        }
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
}
