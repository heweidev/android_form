package com.hewei.formblocks.form;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.print.PrinterId;

import com.hewei.formblocks.AddressEditActivity;
import com.hewei.formblocks.Constants;
import com.hewei.formblocks.blocks.AddressBlock;
import com.hewei.formblocks.blocks.BlockListener;
import com.hewei.formblocks.model.Address;

public class CustomerForm extends BaseForm {
    private static int REQUEST_GET_ADDRESS = 10001;
    private AddressBlock mAddressBlock;

    public CustomerForm(Context context) {
        super(context);
    }

    public void init() {
        getBlock("address").setEventListener(new BlockListener() {
            @Override
            public void onEvent(String event, Object data) {

            }
        });
    }



    private void startAddressEditActivity(Address address) {
        Intent intent = new Intent(mContext, AddressEditActivity.class)
                .putExtra(Constants.EXTRA_DATA, address);
        mContext.startActivity(intent);
    }
}
