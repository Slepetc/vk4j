package org.vk4j.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.ParserBase;
import org.vk4j.api.VkException;
import org.vk4j.responses.Wall;


public class WallParser extends ParserBase<Wall> {
    
    @Override
    public Wall parse(Object object) {

        if (object instanceof Integer) {
        	return null;
        }
    	if (!(object instanceof JSONObject)) {
            throw new VkException("MessageParser doesn't know how to parse not JSONObject");
        }

        JSONObject wall = (JSONObject) object;

        try {

            Wall result = new Wall();
            MediaParser mediaParser = new MediaParser();

            for (String field : Wall.FIELDS) {
                if (wall.has(field)) {
                	if (field.equals(Wall.FIELDS[Wall.MEDIA]))
                		result.put(field, mediaParser.parse(wall.getJSONObject(field)));
                	else
                		result.put(field, wall.getString(field));
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
