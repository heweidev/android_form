package com.hewei.formblocks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.hewei.formblocks.form.BaseForm;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TestForm testForm;
    private UserForm userForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        testForm = new TestForm(this);
        testForm.onSetup((LinearLayout) findViewById(R.id.form_container));

        String[] array = new String[] {
                "A", "B", "C",
                "A", "B", "C",
                "A", "B",
        };

        TextArrayForm form = new TextArrayForm(this);
        form.onSetup((LinearLayout) findViewById(R.id.text_array_container));
        form.bingData(array);

        userForm = new UserForm(this);
        try {
            userForm.onSetup(R.xml.xml_form, (LinearLayout) findViewById(R.id.object_container));
            userForm.bindData(new UserForm.User("Hewei", 34, true));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        testForm.buildMenu(getMenuInflater(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        userForm.onEvent(id);
        return testForm.onEvent(id);
    }
}
