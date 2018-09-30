package com.hewei.formblocks.blocks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hewei.formblocks.AddressEditActivity;
import com.hewei.formblocks.Constants;
import com.hewei.formblocks.R;
import com.hewei.formblocks.form.FormActivity;
import com.hewei.formblocks.model.Address;

public class AddressBlock extends BaseBlock<Address> implements View.OnClickListener {
    private TextView mTextView;
    private int mRequestCode;

    @Override
    public void onData(Address data) {
        if (mTextView != null && data != null) {
            mTextView.setText(data.toString());
        }
    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.address_line, parentView, false);
        mTextView = rootView.findViewById(R.id.tvAddress);
        rootView.setOnClickListener(this);
        return rootView;
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == mRequestCode) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Address address = data.getParcelableExtra(Constants.EXTRA_DATA);
                if (address != null) {
                    setData(address);
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        mRequestCode = FormActivity.getRequestCode();

        Activity activity = (Activity) v.getContext();
        Intent intent = new Intent(activity, AddressEditActivity.class)
                .putExtra(Constants.EXTRA_DATA, getData());
        activity.startActivityForResult(intent, mRequestCode);
    }
}
