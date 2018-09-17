package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hewei.formblocks.R;

import java.util.List;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class KeyValueBlock extends BaseBlock<Object> {
    private TextView mValueView;

    @Override
    public void onData(Object data) {
        if (data == null) {
            return;
        }

        if (mValueView != null && data != null) {
            mValueView.setText(data.toString());
        }
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.key_value_item, parentView, false);
        TextView mKeyView = rootView.findViewById(R.id.tvKey);
        mValueView = rootView.findViewById(R.id.tvValue);

        List<String> args = getArgs();
        if (args != null && args.size() > 0) {
            mKeyView.setText(args.get(0));
        }

        return rootView;
    }
}
