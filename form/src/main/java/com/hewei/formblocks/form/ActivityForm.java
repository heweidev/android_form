package com.hewei.formblocks.form;

/*
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.annotation.MenuRes;
import android.support.annotation.XmlRes;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;
import com.hewei.formblocks.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;



public class ActivityForm extends BaseForm {
    private String title;
    private @MenuRes int menuRes;
    private Class<?> dataClass;

    public ActivityForm(Context context) {
        super(context);
    }

    @Override
    public void onSetup(@XmlRes int xml, LinearLayout container) throws XmlPullParserException, IOException {
        XmlResourceParser xpp = mContext.getResources().getXml(xml);
        int eventType = xpp.getEventType();
        String name;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            name = xpp.getName();
            if(eventType == XmlPullParser.START_DOCUMENT) {

            } else if(eventType == XmlPullParser.START_TAG) {
                if ("formactivity".equals(name)) {
                    title = xpp.getAttributeValue(null, "title");
                    String cls = xpp.getAttributeValue(null, "class");
                    if (!TextUtils.isEmpty(cls)) {
                        try {
                            dataClass = Class.forName(cls);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    String form = xpp.getAttributeValue(null, "form");
                    //mContext.getResources().getXml();
                }
            } else if(eventType == XmlPullParser.END_TAG) {

            } else if(eventType == XmlPullParser.TEXT) {

            }
            eventType = xpp.next();
        }

        xml = R.xml.xml_form;
        super.onSetup(xml, container);
    }

    @Override
    public void buildMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(menuRes, menu);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMenuRes() {
        return menuRes;
    }

    public void setMenuRes(int menuRes) {
        this.menuRes = menuRes;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

    public void setDataClass(Class<?> dataClass) {
        this.dataClass = dataClass;
    }
}
*/
