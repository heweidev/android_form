package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hewei.formblocks.R;

public class FormLabel extends BaseBlock<CharSequence> {
    private TextView mFormTitleView;

    public static String EDIT_EVENT = "_b_edit";

    @Override
    public void onData(CharSequence data) {
        if (mFormTitleView != null) {
            mFormTitleView.setText(data);
        }
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.form_label, parentView, false);
        mFormTitleView = rootView.findViewById(R.id.title);
        rootView.findViewById(R.id.tvEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEvent(EDIT_EVENT, null);
            }
        });
        return rootView;
    }
}
