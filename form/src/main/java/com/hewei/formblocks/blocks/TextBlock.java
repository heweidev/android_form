package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextBlock extends BaseBlock<Object> {
    private TextView mTextView;

    @Override
    public void onData(Object data) {
        if (mTextView != null) {
            mTextView.setText(data.toString());
        }
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        mTextView = new TextView(context);
        mTextView.setText("This is text wiget");
        return mTextView;
    }
}
