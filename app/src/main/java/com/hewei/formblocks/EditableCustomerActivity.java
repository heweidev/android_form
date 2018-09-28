package com.hewei.formblocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hewei.formblocks.form.BaseForm;
import com.hewei.formblocks.form.FormView;

public class EditableCustomerActivity extends AppCompatActivity {
    private BaseForm mForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_customer);
        mForm = ((FormView) findViewById(R.id.form)).getForm();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_commit) {
            Customer customer = mForm.getData(Customer.class);
            Toast.makeText(this, customer.toString(), Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
