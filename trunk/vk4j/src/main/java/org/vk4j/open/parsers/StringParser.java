package org.vk4j.open.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.vk4j.api.VkException;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 9, 2010
 * Time: 4:52:41 PM
 */
public class StringParser extends ParserBase<String> {

    @Override
    public String parse(Object object) {

        if (!(object instanceof String)) {
            throw new VkException("This is not String");
        }

        return (String) object;
    }

    @Override
    public String getFromArray(JSONArray array, int idx) {
        try {
            return array.getString(idx);
        } catch (JSONException e) {
            //TODO: error!
            throw new VkException("JSON exception" + e);
        }
    }
}
