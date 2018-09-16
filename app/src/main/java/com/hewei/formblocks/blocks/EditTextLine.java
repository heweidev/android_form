package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hewei.formblocks.R;

public class EditTextLine extends BaseBlock<CharSequence> {
    private EditText mEditText;

    @Override
    public CharSequence getData() {
        if (mEditText != null) {
            return mEditText.getText();
        } else {
            return super.getData();
        }
    }

    @Override
    public void onData(CharSequence data) {
        mEditText.setText(data);
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mEditText = (EditText) inflater.inflate(R.layout.edit_text, parentView, false);
        return mEditText;
    }
}
