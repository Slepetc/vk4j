package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.LongParser;

public class DeleteMessages extends RequestBase {

    public static final String METHOD = "messages.delete";
    
    public static final String TAG_MID = "mid";
    
    static {
        ParserFactory.register(METHOD, LongParser.class);
    }
    
    public DeleteMessages() {
        super(METHOD);
    }
    
    public static class Builder {
        private DeleteMessages request = new DeleteMessages();

        public DeleteMessages build() {
            return request;
        }

        public Builder setMid(long mid) {
            request.add(TAG_MID, Long.toString(mid));
            return this;
        }
    }    
}
