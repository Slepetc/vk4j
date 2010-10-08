package org.vk4j.open.requests;

import java.util.Collection;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.LongParser;

public class MarkAsNewMessages extends RequestBase {

    public static final String METHOD = "messages.markAsNew";
    
    public static final String TAG_MIDS = "mids";
    
    static {
        ParserFactory.register(METHOD, LongParser.class);
    }
    
    public MarkAsNewMessages() {
        super(METHOD);
    }
    
    public static class Builder {
        private MarkAsNewMessages request = new MarkAsNewMessages();

        public MarkAsNewMessages build() {
            return request;
        }

        public Builder setMids(String mids) {
            request.add(TAG_MIDS, mids);
            return this;
        }

        public Builder setUids(Collection mids) {
            StringBuffer sb = new StringBuffer();
            for (Object mid : mids) {
                sb.append(mid).append(",");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            setMids(sb.toString());
            return this;
        }
    }    
}
