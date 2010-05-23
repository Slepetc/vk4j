package org.vk4j.api;

import java.util.Map;

/**
 * Created by Vladimir Grachev.
 * Date: May 23, 2010
 * Time: 5:23:24 PM
 */
public interface Request {
    public Map<String, String> getValues();
    public void add(String key, String value);
    public String getMethod();
}
