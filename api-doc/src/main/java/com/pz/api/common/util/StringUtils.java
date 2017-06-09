package com.pz.api.common.util;

/**
 * Created by lp on 2017/6/9.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || String.valueOf(obj).trim().length() == 0;
    }
}
