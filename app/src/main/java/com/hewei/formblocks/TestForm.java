package com.hewei.formblocks;


import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hewei.formblocks.annotations.ActionMenu;
import com.hewei.formblocks.annotations.Block;
import com.hewei.formblocks.annotations.Layout;
import com.hewei.formblocks.annotations.Title;
import com.hewei.formblocks.blocks.DataListener;
import com.hewei.formblocks.blocks.EditTextLine;
import com.hewei.formblocks.blocks.ReadOnlyKVLine;
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
    private ReadOnlyKVLine readOnlyKVLine;

    @Block(factory = SpinnerFactory.class)
    private SpinnerLine spinnerLine;

    @Block
    private EditTextLine editTextLine;

    @Override
    public void onSetup(LinearLayout container) {
        super.onSetup(container);

        readOnlyKVLine.setData("data from form");
        editTextLine.setData("深圳");
        spinnerLine.setData(new SpinnerLine.SpinnerItem(2, null));

        spinnerLine.setDataListener(new DataListener<SpinnerLine.SpinnerItem>() {
            @Override
            public void onDataChanged(SpinnerLine.SpinnerItem data) {
                editTextLine.setData(data.desc);
            }
        });
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
