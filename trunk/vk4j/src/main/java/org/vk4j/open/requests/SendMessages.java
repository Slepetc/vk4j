package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.LongParser;

public class SendMessages extends RequestBase {

    public static final String METHOD = "messages.send";
    
    public static final String TAG_UID = "uid";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_TITLE = "title";
    public static final String TAG_TYPE = "type";
    
    public static final int TYPE_REGULAR = 0;
    public static final int TYPE_CHAT = 1;
    
    static {
        ParserFactory.register(METHOD, LongParser.class);
    }
    
    public SendMessages() {
        super(METHOD);
    }
    
    public static class Builder {
        private SendMessages request = new SendMessages();

        public SendMessages build() {
            return request;
        }

        public Builder setUid(long uid) {
            request.add(TAG_UID, Long.toString(uid));
            return this;
        }

        public Builder setMessage(String message) {
            request.add(TAG_MESSAGE, message);
            return this;
        }
        
        public Builder setTitle(String title) {
            request.add(TAG_TITLE, title);
            return this;
        }

        public Builder setType(int type) {
            request.add(TAG_TYPE, Integer.toString(type));
            return this;
        }
    }    
}
