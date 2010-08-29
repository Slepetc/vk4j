package org.vk4j.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.vk4j.api.ParserBase;
import org.vk4j.api.VkException;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 10, 2010
 * Time: 5:18:34 PM
 */
public class IntParser extends ParserBase<Integer> {

    @Override
    public Integer parse(Object object) {

        if (!(object instanceof Integer)) {
            throw new VkException("This is not Integer");
        }

        return (Integer) object;
    }

    @Override
    public Integer getFromArray(JSONArray array, int idx) {
        try {
            return array.getInt(idx);
        } catch (JSONException e) {
            //TODO: error!
            throw new VkException("JSON exception" + e);
        }
    }
}
