package org.vk4j.api;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 18:18:12
 */
public abstract class RequestBase implements Request {
    private TreeMap<String, String> values = new TreeMap<String, String>();

    public Map<String, String> getValues() {
        return values;
    }

    public void add(String key, String value) {
        values.put(key, value);
    }

    public void add(String key, List<String> values) {
        StringBuilder sb = new StringBuilder();

        for (String value : values) {
            sb.append(value).append(",");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        add(key, sb.toString());
    }

    public String getMethod() {
        return values.get("method");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
