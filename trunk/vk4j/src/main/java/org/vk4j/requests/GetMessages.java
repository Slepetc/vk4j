package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.parsers.ListParser;
import org.vk4j.parsers.MessageParser;

public class GetMessages extends RequestBase {

    public static final String METHOD = "messages.get";
    
    public static final String TAG_OUT = "out";
    public static final String TAG_OFFSET = "offset";
    public static final String TAG_COUNT = "count";
    public static final String TAG_FILTERS = "filters";
    public static final String TAG_PREVIEW_LENGTH = "preview_length";
    public static final String TAG_TIME_OFFSET = "time_offset";
    
    // Filters    
    public static final int FILTER_UNREAD = 1;
    public static final int FILTER_NOT_CHAT = 2;
    public static final int FILTER_FROM_FRIENDS = 4;        
    
    static {
        ParserFactory.register(METHOD, ListParser.class, MessageParser.class);
    }
    
    public GetMessages() {
        super(METHOD);
    }
    
    public static class Builder {
        private GetMessages request = new GetMessages();

        public GetMessages build() {
            return request;
        }

        public Builder setOut(boolean out) {
            request.add(TAG_OUT, out?"1":"0");
            return this;
        }

        public Builder setOffset(int offset) {
            request.add(TAG_OFFSET, Integer.toString(offset));
            return this;
        }
        
        public Builder setCount(int count) {
            request.add(TAG_COUNT, Integer.toString(count));
            return this;
        }

        public Builder setFilters(int filters) {
            request.add(TAG_FILTERS, Integer.toString(filters));
            return this;
        }

        public Builder setPreviewLength(int previewLength) {
            request.add(TAG_PREVIEW_LENGTH, Integer.toString(previewLength));
            return this;
        }

        public Builder setTimeOffset(long timeOffset) {
            request.add(TAG_TIME_OFFSET, Long.toString(timeOffset));
            return this;
        }
    }    
}
