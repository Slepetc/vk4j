package org.vk4j.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.ParserBase;
import org.vk4j.api.VkException;
import org.vk4j.responses.Video;


public class VideoParser extends ParserBase<Video> {
    
    @Override
    public Video parse(Object object) {

        if (object instanceof Integer) {
        	return null;
        }
    	if (!(object instanceof JSONObject)) {
            throw new VkException("MessageParser doesn't know how to parse not JSONObject");
        }

        JSONObject video = (JSONObject) object;

        try {

            Video result = new Video();

            for (String field : Video.FIELDS) {
                if (video.has(field)) {
                    result.put(field, video.getString(field));
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
