package org.vk4j.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.VkException;
import org.vk4j.api.Response;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 23:50:48
 */
public abstract class ParserBase implements Parser {

    public Response parse(String string) {
        try {

            System.out.println("ParserBase.parse");
            System.out.println("string = " + string);

            JSONObject response = new JSONObject(string);
            if (response.has("response")) {
                return parse(response);
            }
            if (response.has("error")) {

                //TODO: here should be vk4j exception, not Runtime
                throw new VkException("Error");
            }
            throw new VkException("Error unknown");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new VkException("Error json");
        }
    }

    public abstract Response parse(JSONObject object);
}
