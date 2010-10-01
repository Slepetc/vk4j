package org.vk4j.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.VkException;
import org.vk4j.responses.Profile;

/**
 * Created by Vladimir Grachev.
 * Date: May 29, 2010
 * Time: 1:03:31 AM
 */
public class ProfileParser extends ParserBase<Profile> {
    
    @Override
    public Profile parse(Object object) {

        if (!(object instanceof JSONObject)) {
            throw new VkException("ProfileParser doesn't know how to parse not JSONObject");
        }

        JSONObject profile = (JSONObject) object;

        try {

            Profile result = new Profile();

            for (String field : Profile.FIELDS) {
                if (profile.has(field)) {
                    result.put(field, profile.getString(field));
                }
            }

            return result;
        } catch (JSONException e) {
            //TODO:
            e.printStackTrace();
        }

        return null;
    }
}
