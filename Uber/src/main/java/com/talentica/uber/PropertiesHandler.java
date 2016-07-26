package com.talentica.uber;

import com.google.common.io.Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * Created by aravindp on 26/4/16.
 */

public class PropertiesHandler {

    private static Properties properties;

    private static String defaultFiles[] = {"application.properties"};

    static {
        if (properties == null) {
            properties = new Properties();
            load(defaultFiles);
        }
    }

    private static void load(String files[]) {
        for (String file : files) {
            try {
                URL resource = Resources.getResource(file);
                properties.load(new InputStreamReader(new FileInputStream(resource.getPath())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadProperties(String files[]) {
        if (properties == null) {
            properties = new Properties();
        }
        load(files);
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }
}
