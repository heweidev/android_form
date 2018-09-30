package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hewei.formblocks.data.ListItem;

public class SpinnerLine extends BaseBlock<ListItem> {
    private Spinner mSpinner;
    private ListItem[] mItems;

    public SpinnerLine(ListItem[] items) {
        mItems = items;
    }

    @Override
    public ListItem getData() {
        int pos = mSpinner.getSelectedItemPosition();
        return mItems[pos];
    }

    @Override
    public void onData(ListItem data) {
        if (mItems == null) {
            return;
        }

        int position = 0;
        for (ListItem item : mItems) {
            if (item.getId() == data.getId()) {
                mSpinner.setSelection(position);
                break;
            }
            position++;
        }
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        mSpinner = new Spinner(context);
        String[] itemTextArray = new String[mItems.length];
        for (int i = 0; i < mItems.length; i++) {
            itemTextArray[i] = mItems[i].getDesc();
        }
        mSpinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, itemTextArray));
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(mItems[position], false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return mSpinner;
    }

    public static class SimpleListItem implements ListItem {
        private final String id;
        private final String desc;

        public SimpleListItem(String id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getDesc() {
            return desc;
        }
    }
}
