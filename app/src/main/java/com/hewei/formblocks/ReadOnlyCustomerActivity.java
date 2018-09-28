package com.hewei.formblocks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hewei.formblocks.form.FormView;
import com.hewei.formblocks.model.Address;
import com.hewei.formblocks.model.EIdType;

public class ReadOnlyCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_view);

        FormView formView = findViewById(R.id.formView);

        final Customer customer = new Customer();
        customer.name = "张三";
        customer.idType = EIdType.ID;
        customer.idNo = "123456";
        customer.addr = Address.INSTANCE;
        customer.mobileNo = "13400000000";

        formView.getForm().bindData(customer);
    }
}
