package com.hewei.formblocks.blocks;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.hewei.formblocks.R;

import java.util.List;

public class KeyEditableValueBlock extends BaseBlock<CharSequence> {
    private EditText mValueView;

    @Override
    public void onData(CharSequence data) {
        if (data == null) {
            return;
        }

        if (mValueView != null && data != null) {
            mValueView.setText(data);
        }
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.key_edit_value, parentView, false);
        TextView mKeyView = rootView.findViewById(R.id.tvKey);
        mValueView = rootView.findViewById(R.id.tvValue);
        mValueView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setData(s);
            }
        });

        List<String> args = getArgs();
        if (args != null && args.size() > 0) {
            mKeyView.setText(args.get(0));
        }

        return rootView;
    }
}
