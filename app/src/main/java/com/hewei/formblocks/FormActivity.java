package com.hewei.formblocks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.LinearLayout;
import com.hewei.formblocks.blocks.BaseBlock;
import com.hewei.formblocks.blocks.BlockListener;
import com.hewei.formblocks.data.DataProvider;
import com.hewei.formblocks.data.Editable;
import com.hewei.formblocks.data.ReflactEditable;
import com.hewei.formblocks.form.ActivityForm;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class FormActivity extends AppCompatActivity {
    private ActivityForm activityForm;

    public static final String EXTRA_XML_FORM = "EXTRA_XML_FORM";
    public static final String EXTRA_FORM_DATA = "EXTRA_FORM_DATA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        @XmlRes int xmlRes = getIntent().getIntExtra(EXTRA_XML_FORM, 0);
        final Customer customer = getIntent().getParcelableExtra(EXTRA_FORM_DATA);
        if (xmlRes == 0) {
            finish();
            return;
        }

        activityForm = new ActivityForm(this);

        LinearLayout container = new LinearLayout(this);
        container.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        try {
            activityForm.onSetup(R.xml.form_activity, container);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle(activityForm.getTitle());

        if (customer != null) {
            activityForm.bindDataProvider(new DataProvider<Editable<String>>() {
                @Override
                public Editable<String> getData(String key) {
                    return new ReflactEditable(customer, key);
                }
            });
        }

        for (final BaseBlock baseBlock : activityForm.getBlocks()) {
            baseBlock.setEventListener(new BlockListener() {
                @Override
                public void onEvent(String event, Object data) {
                    if (BlockListener.EVENT_DATA_CHANGE.equals(event)) {
                        ((Editable<String>) baseBlock.getData()).set(data.toString());
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        activityForm.buildMenu(getMenuInflater(), menu);
        return true;
    }

}
