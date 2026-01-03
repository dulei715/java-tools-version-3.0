package cn.edu.dll.io.read;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesRead {
    private Properties props;

    public PropertiesRead(String path) {
        props = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get(path))) {
            props.load(in); // 加载配置
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String get(String key) {
        return this.props.getProperty(key);
    }

    public Set<String> getKeySet() {
        return props.stringPropertyNames();
    }

    public Set<Map.Entry<Object, Object>> getEntrySet() {
        return props.entrySet();
    }
}
