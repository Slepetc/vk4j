package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.ListParser;
import org.vk4j.open.parsers.NewsParser;

public class GetNews extends RequestBase {

    public static final String METHOD = "activity.getNews";
    
    public static final String TAG_TIMESTAMP = "timestamp";
    public static final String TAG_OFFSET = "offset";
    public static final String TAG_COUNT = "count";
    
    static {
        ParserFactory.register(METHOD, ListParser.class, NewsParser.class);
    }
    
    public GetNews() {
        super(METHOD);
    }

    public static class Builder {
        private GetNews request = new GetNews();

        public GetNews build() {
            return request;
        }

        public Builder setTimeStamp(long timeStamp) {
            request.add(TAG_TIMESTAMP, Long.toString(timeStamp));
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
    }
}
