package com.hewei.formblocks.form;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.XmlRes;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hewei.form.R;

public class FormView extends LinearLayout {
    private BaseForm mForm;

    public FormView(Context context) {
        super(context);
        init(null);
    }

    public FormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FormView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(VERTICAL);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FormView);
            int resId = a.getResourceId(R.styleable.FormView_formXml, 0);
            if (resId != 0) {
                setXml(resId);
            }

            a.recycle();
        }
    }

    public BaseForm getForm() {
        return mForm;
    }

    public void setXml(@XmlRes int resId) {
        removeAllViews();

        if (resId != 0) {
            mForm = new BaseForm(getContext());
            mForm.add(new XmlProvider(getContext(), resId), this);
        }
    }
}
