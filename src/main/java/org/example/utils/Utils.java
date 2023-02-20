package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
    public static Properties loadProps(String path){
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return props;
    }
}
