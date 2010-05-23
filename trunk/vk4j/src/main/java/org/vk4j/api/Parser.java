package org.vk4j.api;

import org.json.JSONObject;
import org.vk4j.api.Response;

/**
 * Created by Vladimir Grachev.
 * Date: May 23, 2010
 * Time: 5:27:07 PM
 */
public interface Parser {
    public Response parse(String string);
    public Response parse(JSONObject object);
}
