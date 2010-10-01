package org.vk4j.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.VkException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir Grachev.
 * Date: Jul 5, 2010
 * Time: 9:42:31 PM
 */
public class CitiesParser extends ParserBase<Map<Integer, String>> {

    public static final String TAG_CID = "cid";
    public static final String TAG_NAME = "name";

    @Override
    public Map<Integer, String> parse(Object object) {

        if (!(object instanceof JSONArray)) {
            return Collections.emptyMap();
        }

        try {
            JSONArray array = (JSONArray) object;

            HashMap<Integer, String> result = new HashMap<Integer, String>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject city = array.getJSONObject(i);
                result.put(city.getInt(TAG_CID), city.getString(TAG_NAME));
            }

            return result;
        } catch (JSONException e) {
            //TODO:
             throw new VkException("Cities array parse error!");
            //return Collections.emptyMap();
        }
    }
}
