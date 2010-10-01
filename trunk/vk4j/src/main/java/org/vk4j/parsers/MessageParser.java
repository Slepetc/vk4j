package org.vk4j.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.VkException;
import org.vk4j.responses.Message;

public class MessageParser extends ParserBase<Message> {
	
	private List<String> parseAttachments(JSONArray attachments) throws JSONException {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < attachments.length(); i++) {
			String attachment = attachments.getString(i);			
			if (attachment.startsWith("[[") && attachment.endsWith("]]")) {				
				result.add(attachment.substring(2, attachment.length() - 2));
			}
		}
		return result;
	}
    
    @Override
    public Message parse(Object object) {

        if (object instanceof Integer) {
        	return null;
        }
    	if (!(object instanceof JSONObject)) {
            throw new VkException("MessageParser doesn't know how to parse not JSONObject");
        }

        JSONObject message = (JSONObject) object;
        // Log.d("org.vk4j.parsers.MessageParser", message.toString());

        try {

            Message result = new Message();
                        
            for (String field : Message.FIELDS) {
                if (message.has(field)) {
                	if (field.equals(Message.FIELDS[Message.ATTACHMENTS])) {
                		// attachments is encoded weird like [[[photouid_id]]]
                		// so it is an array in array in array. let's try to parse it 
                		// to a list of strings
                		List<String> attachments = parseAttachments(message.getJSONArray(field));
                		result.put(field, attachments);
                	}
                	else
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
