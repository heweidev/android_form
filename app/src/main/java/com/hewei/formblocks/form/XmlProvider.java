package com.hewei.formblocks.form;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.annotation.XmlRes;
import android.text.TextUtils;

import com.hewei.formblocks.blocks.BlockFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class XmlProvider implements BatchProvider {
    private @XmlRes int mXmlRes;
    private Context mContext;

    public XmlProvider(Context context, int xmlRes) {
        mContext = context;
        mXmlRes = xmlRes;
    }

    @Override
    public List<BlockProvider> getProviders() {
        try {
            return getProvidersImpl();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<BlockProvider> getProvidersImpl() throws XmlPullParserException, IOException {
        XmlResourceParser xpp = mContext.getResources().getXml(mXmlRes);
        int eventType = xpp.getEventType();
        String name;
        String id;
        String type = null;
        String factory = null;
        String desc;
        String tempType;
        String tempFactory;
        int size;
        List<BlockProvider> providerList = new ArrayList<>();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            name = xpp.getName();
            if(eventType == XmlPullParser.START_DOCUMENT) {

            } else if(eventType == XmlPullParser.START_TAG) {
                if ("forms".equals(name)) {
                    type = xpp.getAttributeValue(null, "type");
                    factory = xpp.getAttributeValue(null, "factory");
                } else if ("item".equals(name)) {
                    id = xpp.getAttributeValue(null, "id");
                    size = xpp.getAttributeIntValue(null, "size", 1);
                    desc = xpp.getAttributeValue(null, "desc");

                    // 优先使用item的，如果没有使用form的
                    tempType = xpp.getAttributeValue(null, "type");
                    if (tempType != null) {
                        type = tempType;
                    }
                    tempFactory = xpp.getAttributeValue(null, "factory");
                    if (tempFactory != null) {
                        factory = tempFactory;
                    }

                    try {
                        Class<? extends BlockFactory<?>> clsFactory = null;
                        if (!TextUtils.isEmpty(factory)) {
                            clsFactory = (Class<? extends BlockFactory<?>>) Class.forName(factory);
                        }

                        providerList.add(new SimpleBlockProvider(id, Class.forName(type),
                                clsFactory, size, Collections.singletonList(desc)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if(eventType == XmlPullParser.END_TAG) {

            } else if(eventType == XmlPullParser.TEXT) {

            }
            eventType = xpp.next();
        }

        return providerList;
    }
}
