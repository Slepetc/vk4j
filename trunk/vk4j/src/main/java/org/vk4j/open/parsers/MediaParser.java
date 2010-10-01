package org.vk4j.open.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.VkException;
import org.vk4j.open.responses.Media;

public class MediaParser extends ParserBase<Media> {
    
    @Override
    public Media parse(Object object) {

    	if (!(object instanceof JSONObject)) {
            throw new VkException("MessageParser doesn't know how to parse not JSONObject");
        }

        JSONObject media = (JSONObject) object;

        try {

            Media result = new Media();
            
            for (String field : Media.FIELDS) {
                if (media.has(field)) {
                    result.put(field, media.getString(field));
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
