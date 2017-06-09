package com.pz.api.common.util;

/**
 * 版本处理
 */
public class VersionUtil {

    private String env;

    private VersionUtil() {
        env = PropertiesUtil.getValue("api.env");
    }

    private static final VersionUtil versionUtil = new VersionUtil();

    public static VersionUtil getInstance() {
        return versionUtil;
    }

    /**
     * 版本号转换，与程序内部对应
     * @param appVersion
     * @return
     */
    public String getAppVersion(String appVersion) {
        if (StringUtils.isNullOrEmpty(appVersion)) {
            return null;
        }
        if (!"RLS".equalsIgnoreCase(env)) {
            appVersion = appVersion.substring(0, appVersion.lastIndexOf("."));
        }
        return "V" + appVersion + ",";
    }

    public String getVersion(String version) {
        return "V" + version + ",";
    }
}
