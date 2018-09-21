package com.hewei.formblocks;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.hewei.formblocks.blocks.BaseBlock;
import com.hewei.formblocks.blocks.BlockListener;
import com.hewei.formblocks.blocks.FormLabel;
import com.hewei.formblocks.form.BaseForm;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Map;

public class EditableForm extends BaseForm implements BlockListener {
    public EditableForm(Context context) {
        super(context);
    }

    @Override
    public void onSetup(int xml, LinearLayout container) throws XmlPullParserException, IOException {
        FormLabel label = new FormLabel();
        View labelView = label.getView(container.getContext(), container);
        label.setData("客户详情");
        label.setEventListener(this);
        if (labelView != null) {
            container.addView(labelView);
            saveBlock("label", label);
        }

        super.onSetup(xml, container);

        for (BaseBlock baseBlock : mBlocks.values()) {
            baseBlock.setEventListener(this);
        }
    }

    @Override
    public void onEvent(String event, Object data) {
        if (FormLabel.EDIT_EVENT.equals(event)) {
            Toast.makeText(mContext, "data changed", Toast.LENGTH_SHORT).show();
        }
    }
}
