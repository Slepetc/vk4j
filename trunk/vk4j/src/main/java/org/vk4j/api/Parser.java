package org.vk4j.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: May 23, 2010
 * Time: 5:27:07 PM
 */
public interface Parser<T extends Object> {

    public T parse(String string);
    public T parse(Object object);
    public <T1 extends Object> List<T1> parseArray(Object object, String type);
    public T getFromArray(JSONArray array, int idx);
    public void setInnerType(String innerType);

} //Parser
