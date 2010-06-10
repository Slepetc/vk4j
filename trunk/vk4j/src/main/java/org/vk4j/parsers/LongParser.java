package org.vk4j.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.vk4j.api.ParserBase;
import org.vk4j.api.ParserFactory;
import org.vk4j.api.VkException;
import org.vk4j.requests.GetFriends;

/**
 * Created by Vladimir Grachev.
 * Date: May 26, 2010
 * Time: 12:47:48 PM
 */
public class LongParser extends ParserBase<Long> {

    @Override
    public Long parse(Object object) {

        if (!(object instanceof Long)) {
            throw new VkException("This is not Long");
        }

        return (Long) object;
    }

    @Override
    public Long getFromArray(JSONArray array, int idx) {
        try {
            return array.getLong(idx);
        } catch (JSONException e) {
            //TODO: error!
            throw new VkException("JSON exception" + e);
        }
    }
}
