package org.vk4j.open.requests;

import java.util.Collection;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.LongParser;

public class MarkAsReadMessages extends RequestBase {

    public static final String METHOD = "messages.markAsRead";
    
    public static final String TAG_MIDS = "mids";
    
    static {
        ParserFactory.register(METHOD, LongParser.class);
    }
    
    public MarkAsReadMessages() {
        super(METHOD);
    }
    
    public static class Builder {
        private MarkAsReadMessages request = new MarkAsReadMessages();

        public MarkAsReadMessages build() {
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
