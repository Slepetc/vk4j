package org.vk4j.open.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.Parser;
import org.vk4j.api.ParserFactory;
import org.vk4j.api.VkException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 23:50:48
 */
public abstract class ParserBase<T extends Object> implements Parser<T> {

    protected String innerType;

    public ParserBase () {
        this.innerType = null;
    }

    public void setInnerType(String innerType) {
        this.innerType = innerType;
    }

    public T parse(String string) {
        return Helper.<T>parseJSON(string, this);
    }

    public abstract T parse(Object object);

    public <T1> List<T1> parseArray(Object object, String type) {

        if (object instanceof JSONObject && ((JSONObject) object).length() == 0) {
            return Collections.EMPTY_LIST;
        }

        if (!(object instanceof JSONArray)) {
            throw new VkException("ListParser can't parse not JSONArray object");
        }

        return Helper.<T1>parseArray((JSONArray) object, ParserFactory.<T1>newParser(type));
    }

    public T getFromArray(JSONArray array, int idx) {
        return Helper.<T>getFromArray(array, idx, this);
    }

    public static class Helper {
        private Helper() {} // IMPL NOTE instantiation prohibited

        protected static<T extends Object> T parseJSON(String string, Parser<T> parser) {
            try {

                JSONObject response = new JSONObject(string);
                if (response.has("response")) {
                    return parser.parse(response.get("response"));
                }
                if (response.has("error")) {
                    throw new VkException("Error:" + string);
                }
                throw new VkException("Error unknown");
            } catch (JSONException e) {
                throw new VkException("Error json");
            }
        }

        protected static <T1 extends Object> List<T1> parseArray(JSONArray array, Parser<T1> parser) {
            List<T1> result = new ArrayList<T1>();

            for (int i = 0; i < array.length(); i++) {
                T1 item = parser.getFromArray(array, i);
                if (item == null) {
                    continue;
                }
                result.add(item);
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
}
