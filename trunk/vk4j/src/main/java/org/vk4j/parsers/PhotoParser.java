package org.vk4j.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.ParserBase;
import org.vk4j.api.VkException;
import org.vk4j.responses.Photo;


public class PhotoParser extends ParserBase<Photo> {
    
    @Override
    public Photo parse(Object object) {

    	if (!(object instanceof JSONObject)) {
            throw new VkException("MessageParser doesn't know how to parse not JSONObject");
        }

        JSONObject photo = (JSONObject) object;

        try {

            Photo result = new Photo();

            for (String field : Photo.FIELDS) {
                if (photo.has(field)) {
                    result.put(field, photo.getString(field));
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
