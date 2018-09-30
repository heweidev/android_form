package com.hewei.formblocks;

import android.text.TextUtils;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class Utils {
    public static String field2Method(String prefix, String fieldName) {
        if (TextUtils.isEmpty(fieldName)) {
            return null;
        }

        String name =  prefix + Character.toUpperCase(fieldName.charAt(0));
        if (fieldName.length() > 1) {
            return name + fieldName.substring(1);
        } else {
            return name;
        }
    }

    public static String tripPrefix(String prefix, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }

        if (TextUtils.isEmpty(prefix)) {
            return str;
        }

        int prefixLen = prefix.length();
        if (str.length() < prefixLen) {
            return null;
        }

        if (prefix.equals(str.substring(0, prefixLen))) {
            String target = str.substring(prefixLen);
            return Character.toLowerCase(target.charAt(0)) + target.substring(1);
        }

        return null;
    }
}
