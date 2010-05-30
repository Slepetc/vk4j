package org.vk4j.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.VkException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 23:50:48
 */
public abstract class ParserBase implements Parser {

    public Object parse(String string) {
        try {

            JSONObject response = new JSONObject(string);
            if (response.has("response")) {
                return parse(response.get("response"));
            }
            if (response.has("error")) {
                throw new VkException("Error");
            }
            throw new VkException("Error unknown");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new VkException("Error json");
        }
    }

    public abstract Object parse(Object object);

    public <T> List<T> parseArray(Object object, String type) {

        if (!(object instanceof JSONArray)) {
            throw new VkException("ListParser can't parse not JSONArray object");
        }

        List<T> result = new ArrayList<T>();

        JSONArray array = (JSONArray) object;

        Parser parser = ParserFactory.get(type);

        for (int i = 0; i < array.length(); i++) {
            result.add((T) parser.getFromArray(array, i));
        }

        return result;

    }

    public Object getFromArray(JSONArray array, int idx) {
        try {
            return parse(array.get(idx));
        } catch (JSONException e) {
            //TODO: error!
            throw new VkException("JSON exception" + e);
        }
    }
}
