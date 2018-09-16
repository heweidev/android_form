package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerLine extends BaseBlock<SpinnerLine.SpinnerItem> {
    private Spinner mSpinner;
    private SpinnerItem[] mItems;

    public SpinnerLine(SpinnerItem[] items) {
        mItems = items;
    }

    @Override
    public SpinnerItem getData() {
        int pos = mSpinner.getSelectedItemPosition();
        return mItems[pos];
    }

    @Override
    public void onData(SpinnerItem data) {
        if (mItems == null) {
            return;
        }

        int position = 0;
        for (SpinnerItem item : mItems) {
            if (item.id == data.id) {
                mSpinner.setSelection(position);
                break;
            }
            position++;
        }
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        mSpinner = new Spinner(context);
        mSpinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, mItems));
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(mItems[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return mSpinner;
    }

    public static final class SpinnerItem {
        public SpinnerItem(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public int id;
        public String desc;

        @Override
        public String toString() {
            return desc;
        }
    }
}
