package com.hewei.formblocks;


import android.content.Context;
import android.util.Pair;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hewei.formblocks.annotations.ActionMenu;
import com.hewei.formblocks.annotations.Block;
import com.hewei.formblocks.annotations.Layout;
import com.hewei.formblocks.annotations.Title;
import com.hewei.formblocks.blocks.KeyValueBlock;
import com.hewei.formblocks.blocks.BlockListener;
import com.hewei.formblocks.blocks.EditTextLine;
import com.hewei.formblocks.blocks.TextBlock;
import com.hewei.formblocks.blocks.factory.SpinnerFactory;
import com.hewei.formblocks.blocks.SpinnerLine;
import com.hewei.formblocks.form.BaseForm;

@Title("Hello World!")
@Layout(R.layout.test_form)
@ActionMenu(R.menu.menu_main)
public class TestForm extends BaseForm {
    public TestForm(Context context) {
        super(context);
    }

    @Block
    private TextBlock readOnlyKVLine;

    @Block(factory = SpinnerFactory.class)
    private SpinnerLine spinnerLine;

    @Block
    private EditTextLine editTextLine;

    @Block
    private KeyValueBlock keyValueBlock;

    @Override
    public void onSetup(LinearLayout container) {
        super.onSetup(container);

        readOnlyKVLine.onData("data from form");
        editTextLine.setData("深圳");
        spinnerLine.setData(new SpinnerLine.SpinnerItem(2, null));
        spinnerLine.setEventListener(new BlockListener<SpinnerLine.SpinnerItem>() {
            @Override
            public void onEvent(String event, SpinnerLine.SpinnerItem data) {
                if (BlockListener.EVENT_DATA_CHANGE.equals(event)) {
                    editTextLine.setData(data.desc);
                }
            }
        });
        keyValueBlock.setData(new Pair<String, Object>("姓名", "Hewei"));
    }

    @Override
    public boolean onEvent(int id) {
        if (id == R.id.action_commit) {
            //Toast.makeText(mContext, "setting", Toast.LENGTH_SHORT).show();

            String editString = editTextLine.getData().toString();
            SpinnerLine.SpinnerItem item = spinnerLine.getData();

            Toast.makeText(mContext, "editString = " + editString + ", spinnerItem = " + item,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "action " + id, Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
