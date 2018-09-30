package com.hewei.formblocks;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hewei.formblocks.form.BaseForm;
import com.hewei.formblocks.form.FormActivity;
import com.hewei.formblocks.form.FormView;
import com.hewei.formblocks.model.Address;

public class AddressEditActivity extends FormActivity {
    private BaseForm mForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FormView formView = new FormView(this);
        formView.setXml(R.xml.address_edit_form);
        setContentView(formView);

        mForm = formView.getForm();
        Address address = getIntent().getParcelableExtra(Constants.EXTRA_DATA);
        if (address != null) {
            mForm.bindData(address);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_commit) {
            Address address = mForm.getData(Address.class);
            if (address != null) {
                Intent intent = new Intent();
                intent.putExtra(Constants.EXTRA_DATA, address);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public BaseForm getForm() {
        return mForm;
    }
}
