package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private Properties prop;


    public GetProperties() {
        init();
    }

    private void init() {
        String path = "";
        if(!GetOSName.getOsName().startsWith("Windows")){
           path = "/src/test/resources/config.properties";
        } else {
           path = "\\src\\test\\resources\\config.properties";
        }
        prop = new Properties();
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + path)) {

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
