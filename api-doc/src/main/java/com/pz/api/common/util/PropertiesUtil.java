package com.pz.api.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件解析
 */
public class PropertiesUtil {
    private static PropertiesUtil me;
    private static final String SOURCE_NAME = "/conf/setting-web.properties";
    private static Properties props = new Properties();

    private PropertiesUtil() {
        init(SOURCE_NAME);
    }

    public static PropertiesUtil getInstance() {
        return null == me ? me = new PropertiesUtil() : me;
    }

    private static void init(String name) {
        InputStream ips;
        ClassLoader loader = PropertiesUtil.class.getClassLoader();
        ips = loader.getResourceAsStream(name);
        try {
            props.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        getInstance();
        return props.getProperty(key);
    }
}
