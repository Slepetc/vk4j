package org.vk4j.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.ParserBase;
import org.vk4j.api.VkException;
import org.vk4j.responses.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: May 29, 2010
 * Time: 1:03:31 AM
 */
public class ProfileParser extends ParserBase {
    
    public static final String ID = "Profile";

    @Override
    public Profile parse(Object object) {

        if (!(object instanceof JSONObject)) {
            throw new VkException("ProfileParser doesn't know how to parse not JSONObject");
        }

        JSONObject profile = (JSONObject) object;

        try {
            Long uid = profile.getLong("uid");
            String firstName = profile.getString("first_name");
            String lastName = profile.getString("last_name");

            return new Profile(uid, firstName, lastName);

        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;    }

}
