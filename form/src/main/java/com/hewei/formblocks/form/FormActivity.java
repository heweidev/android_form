package com.hewei.formblocks.form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.hewei.formblocks.blocks.BaseBlock;

import java.util.Collection;

public abstract class FormActivity extends AppCompatActivity {
    private static int sInitRequestCode = 1000;

    public static int getRequestCode() {
        return sInitRequestCode++;
    }

    public abstract BaseForm getForm();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        BaseForm form = getForm();
        if (form == null) {
            return;
        }
        Collection<BaseBlock<?>> blocks = form.getBlocks();
        if (blocks == null) {
            return;
        }

        for (BaseBlock<?> block : blocks) {
            if (block.onActivityResult(requestCode, resultCode, data)) {
                break;
            }
        }
    }
}
