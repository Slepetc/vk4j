package org.vk4j.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.ParserBase;
import org.vk4j.api.VkException;
import org.vk4j.responses.Message;


public class MessageParser extends ParserBase<Message> {
    
    @Override
    public Message parse(Object object) {

        if (object instanceof Integer) {
        	return null;
        }
    	if (!(object instanceof JSONObject)) {
            throw new VkException("MessageParser doesn't know how to parse not JSONObject");
        }

        JSONObject message = (JSONObject) object;

        try {

            Message result = new Message();

            for (String field : Message.FIELDS) {
                if (message.has(field)) {
                    result.put(field, message.getString(field));
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
