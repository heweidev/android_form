package com.hewei.formblocks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.hewei.formblocks.blocks.EditTextLine;
import com.hewei.formblocks.blocks.factory.ItemsSet;
import com.hewei.formblocks.form.BaseForm;
import com.hewei.formblocks.form.XmlProvider;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private TestForm testForm;
    private UserForm userForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ItemsSet.init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick(R.id.readOnlyCustomerView)
    public void onReadOnlyCustomerView() {
        Intent intent = new Intent(this, ReadOnlyCustomerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.editableCustomerView)
    public void onEditableClick() {
        Intent intent = new Intent(this, EditableCustomerActivity.class);
        startActivity(intent);
    }

    private void testOld() {
        testForm = new TestForm(this);
        testForm.add((LinearLayout) findViewById(R.id.form_container));

        String[] array = new String[] {
                "A", "B", "C",
                "A", "B", "C",
                "A", "B",
        };

        TextArrayForm form = new TextArrayForm(this);
        form.add((LinearLayout) findViewById(R.id.text_array_container));
        form.bingData(array);

        userForm = new UserForm(this);
        userForm.add(new XmlProvider(getApplicationContext(), R.xml.xml_form), (LinearLayout) findViewById(R.id.object_container));
        userForm.bindData(new UserForm.User("Hewei", 34, true));

        BaseForm arrayForm = new BaseForm(this);
        arrayForm.add(new XmlProvider(getApplicationContext(), R.xml.array_form),
                (LinearLayout) findViewById(R.id.rootContainer));

        Customer customer = new Customer();
        customer.name = "Lucy";
        customer.idNo = "110234234u283";
        //customer.idType = "身份证";
        //customer.addr = "深圳市福田区";
        customer.mobileNo = "13500000000";
        arrayForm.bindData(customer);
    }
}
