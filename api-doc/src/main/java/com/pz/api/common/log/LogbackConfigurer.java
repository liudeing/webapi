package com.pz.api.common.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Created by lp on 2017/6/9.
 */
public class LogbackConfigurer {
        private static final String XML_FILE_EXTENSION = ".xml";

        public static void initLogging(String location) throws FileNotFoundException, JoranException {
            String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
            URL url = ResourceUtils.getURL(resolvedLocation);

            if (resolvedLocation.toLowerCase().endsWith(XML_FILE_EXTENSION)) {
                LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                loggerContext.reset();
                JoranConfigurator joranConfigurator = new JoranConfigurator();
                joranConfigurator.setContext(loggerContext);
                joranConfigurator.doConfigure(url);
            }
        }

}
