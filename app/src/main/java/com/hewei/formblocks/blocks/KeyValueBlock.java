package com.hewei.formblocks.blocks;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hewei.formblocks.R;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class KeyValueBlock extends BaseBlock<Pair<String, Object>> {
    private TextView mKeyView;
    private TextView mValueView;

    @Override
    public void onData(Pair<String, Object> data) {
        if (data == null) {
            return;
        }

        if (mKeyView != null && data.first != null) {
            mKeyView.setText(data.first);
        }
        if (mValueView != null && data.second != null) {
            mValueView.setText(data.second.toString());
        }
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.key_value_item, parentView, false);
        mKeyView = rootView.findViewById(R.id.tvKey);
        mValueView = rootView.findViewById(R.id.tvValue);
        return rootView;
    }
}
