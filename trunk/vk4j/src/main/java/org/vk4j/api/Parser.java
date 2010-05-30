package org.vk4j.api;

import org.json.JSONArray;

/**
 * Created by Vladimir Grachev.
 * Date: May 23, 2010
 * Time: 5:27:07 PM
 */
public interface Parser {
    public Object parse(String string);

    public <T extends Object, S extends Object> S parse(T object);

    public <T extends Object> T getFromArray(JSONArray array, int index);
}
