package com.kdn.apc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ApplicationConfig {
    private static final ApplicationConfig instance = new ApplicationConfig();
    private Properties dataBaseConfig = new Properties();
    private int maxNodeLevel;
    public static ApplicationConfig getInstance(){
        return instance;
    }

    public Properties getDataBaseConfig() {
        return dataBaseConfig;
    }

    public int getMaxNodeLevel() {
        return maxNodeLevel;
    }

    private ApplicationConfig(){
        Properties Prop = new Properties();
        try{
            Prop.load(new FileInputStream("./config/application.properties"));
        } catch(IOException e) {
            e.getMessage();
        }
        for (Map.Entry<Object, Object> entry : Prop.entrySet()) {
            String key = entry.getKey().toString().trim();
            String value = entry.getValue().toString().trim();
            switch (key) {
                case "datasource.driver-class-name":
                    dataBaseConfig.setProperty("driver",value);
                    break;
                case "datasource.url":
                    dataBaseConfig.setProperty("url",value);
                    break;
                case "datasource.username":
                    dataBaseConfig.setProperty("name",value);
                    break;
                case "datasource.password":
                    dataBaseConfig.setProperty("password",value);
                    break;
                case "max.node.level":
                    maxNodeLevel = Integer.parseInt(value);
                    break;
            }
        }
    }
}
