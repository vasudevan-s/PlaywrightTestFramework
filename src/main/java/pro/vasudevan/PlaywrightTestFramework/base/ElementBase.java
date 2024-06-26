package pro.vasudevan.PlaywrightTestFramework.base;

import com.microsoft.playwright.Locator;
import org.apache.commons.io.FileUtils;
import pro.vasudevan.PlaywrightTestFramework.config.IPlaywrightTestConfig;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
Created By: Vasudevan Sampath

 ElementBase.java has web element specific methods.
 Also, initializes object repository file (expects app.repo.properties under src/test/resources in your test suite).
 Use getValue() to get a Locator reference string value defined in the properties file
 */
public abstract class ElementBase implements IPlaywrightTestConfig {

    static Map<String, String> map = new HashMap<>();

    protected static void initObjectRepo() throws Exception {
        final Properties properties = new Properties();

        if (map.isEmpty()) {
            properties.load(new FileInputStream(new File(FileUtils.getFile("src", "test", "resources", "app.repo.properties").getAbsolutePath())));
            map = (Map) properties;    // map Properties (key/value pair) to a Hashmap
        }
    }

    public static String getValue(String propertyKey) {
        return map.get(propertyKey);
    }

    @Deprecated
    public static Locator getLocatorRefByKey(String objectKey) {
        return IPlaywrightTestConfig.getPage().locator(map.get(objectKey));
    }

    @Deprecated
    public static Locator getLocatorRef(String objectId) {
        return IPlaywrightTestConfig.getPage().locator(objectId);
    }
}
