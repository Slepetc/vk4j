package org.vk4j.open.login;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 24, 2010
 * Time: 2:51:48 PM
 */
public class LoginParserJson implements LoginParser {

    public Session parse(String json) {

        try {
            JSONObject object = new JSONObject(json);
            return new Session(object.getString(KEY_MID),
                    object.getString(KEY_SID),
                    object.getString(KEY_SECRET),
                    object.getString(KEY_EXPIRE));

        } catch (JSONException e) {
            //TODO: Error!
            e.printStackTrace();
        }

        return null;
    }

}
