package org.vk4j.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.ParserBase;
import org.vk4j.responses.FriendsList;

/**
 * Created by Vladimir Grachev.
 * Date: 28.04.2010
 * Time: 17:30:22
 */
public class FriendsListParser extends ParserBase {

    public FriendsList parse(JSONObject object) {

        FriendsList result = new FriendsList();

        try {
            JSONArray array = object.getJSONArray("response");

            for (int i = 0; i < array.length(); i++) {
                result.add(array.getLong(i));
            }

        } catch (JSONException e) {
            //TODO: error!
            e.printStackTrace();
        }

        return result;
    }
}
