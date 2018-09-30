package com.hewei.formblocks.blocks;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hewei.form.R;

public class EditTextLine extends BaseBlock<String> {
    private EditText mEditText;
    private @LayoutRes int mLayout;

    public EditTextLine() {
        mLayout = R.layout.edit_text;
    }

    public EditTextLine(@LayoutRes int layoutRes) {
        mLayout = layoutRes;
    }

    @Override
    public String getData() {
        if (mEditText != null) {
            return mEditText.getText().toString();
        } else {
            return super.getData();
        }
    }

    @Override
    public void onData(String data) {
        mEditText.setText(data);
    }

    public @LayoutRes int getLayout() {
        return mLayout;
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mEditText = (EditText) inflater.inflate(getLayout(), parentView, false);

        String hint = getArg(0);
        if (!TextUtils.isEmpty(hint)) {
            mEditText.setHint(hint);
        }

        return mEditText;
    }
}
