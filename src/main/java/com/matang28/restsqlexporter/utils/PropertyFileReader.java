package com.matang28.restsqlexporter.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by matan on 01/06/2016.
 */
public class PropertyFileReader {

        public PropertyFileReader(){

        }

        public Properties getPropValues(String propFileName) throws IOException {
            Properties result = new Properties();
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
                if (inputStream != null) {
                    result.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }
            }
            return result;
        }
}
