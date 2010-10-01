package org.vk4j.open.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.VkException;
import org.vk4j.open.responses.News;


public class NewsParser extends ParserBase<News> {
    
    @Override
    public News parse(Object object) {

        if (object instanceof Integer) {
        	return null;
        }
    	if (!(object instanceof JSONObject)) {
            throw new VkException("MessageParser doesn't know how to parse not JSONObject");
        }

        JSONObject news = (JSONObject) object;

        try {

            News result = new News();

            for (String field : News.FIELDS) {
                if (news.has(field)) {
                    result.put(field, news.getString(field));
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
