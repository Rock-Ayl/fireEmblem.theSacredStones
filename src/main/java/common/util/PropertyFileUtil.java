package common.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.function.Function;

/**
 * 操作properties的工具类
 * create by Rock-Ayl 2019-9-2
 */
public class PropertyFileUtil extends Properties {

    //获取战斗配置文件
    public static PropertyFileUtil BattleProperties = new PropertyFileUtil().use("battle.properties");

    public PropertyFileUtil() {
        defaults = new Properties();
    }

    public PropertyFileUtil use(String fileName) {
        try {
            InputStream stream = PropertyFileUtil.class.getResourceAsStream("/" + fileName);
            InputStreamReader in = new InputStreamReader(stream, "UTF-8");
            defaults.load(in);
            in.close();
        } catch (IOException e) {
            System.out.println("使用操作配置文件错误.");
        }
        return this;
    }

    public String asString(String name, String defaultValue) {
        return getOrDefault(name, defaultValue, String::toString);
    }

    public int asInt(String name, int defaultValue) {
        return getOrDefault(name, defaultValue, Integer::parseInt);
    }

    public boolean asBool(String name, boolean defaultValue) {
        return getOrDefault(name, defaultValue, Boolean::parseBoolean);
    }

    public <R> R get(String key, Function<String, R> f) {
        String value = defaults.getProperty(key);
        R result = f.apply(value);
        return result;
    }

    public <R> R getOrDefault(String key, R defaultValue, Function<String, R> f) {
        String value = defaults.getProperty(key);
        String resultValue = value == null ? defaultValue.toString() : value;
        R result = f.apply(resultValue);
        return result;
    }

}
