package ru.javawebinar.basejava.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private static final String PROPS = ".\\config\\resumes.properties";
    private final File storageDir;
    private final String url;
    private final String user;
    private final String password;
    private final Properties props = new Properties();

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + new File(PROPS).getAbsolutePath());
        }
    }

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
