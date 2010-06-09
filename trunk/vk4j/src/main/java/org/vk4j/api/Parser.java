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

    public static class Helper {
        private Helper() {} // IMPL NOTE instantiation prohibited

        protected static<T extends Object> T parse(String string, Parser<T> parser) {
            try {

                JSONObject response = new JSONObject(string);
                if (response.has("response")) {
                    return parser.parse(response.get("response"));
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

        protected static <T1 extends Object> List<T1> parseArray(JSONArray array, Parser<T1> parser) {
            List<T1> result = new ArrayList<T1>();

            for (int i = 0; i < array.length(); i++) {
                result.add(parser.getFromArray(array, i));
            }

            return result;

        }

        protected static <T1 extends Object> T1 getFromArray(JSONArray array, int idx, Parser<T1> parser) {
            try {
                return parser.parse(array.get(idx));
            } catch (JSONException e) {
                //TODO: error!
                throw new VkException("JSON exception" + e);
            }
        }
    } // Helper
} //Parser
