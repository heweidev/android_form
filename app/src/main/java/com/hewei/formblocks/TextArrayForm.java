package com.hewei.formblocks;

import android.content.Context;

import com.hewei.formblocks.annotations.Block;
import com.hewei.formblocks.blocks.TextBlock;
import com.hewei.formblocks.form.BaseForm;

public class TextArrayForm extends BaseForm {
    @Block(size = 10)
    private TextBlock[] mItems;

    public TextArrayForm(Context context) {
        super(context);
    }

    public void bingData(String[] strArray) {
        if (mItems == null) {
            return;
        }

        int len = strArray.length;
        int index = 0;
        for (TextBlock line : mItems) {
            if (index < len) {
                line.onData(strArray[index]);
            }
            index++;
        }

        if (len != mItems.length) {
            // ToDo data error!
        }
    }
}
