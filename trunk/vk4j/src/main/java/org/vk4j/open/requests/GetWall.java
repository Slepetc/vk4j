package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.ListParser;
import org.vk4j.open.parsers.WallParser;

public class GetWall extends RequestBase {

    public static final String METHOD = "wall.get";
    
    public static final String TAG_OWNER_ID = "owner_id";
    public static final String TAG_OFFSET = "offset";
    public static final String TAG_COUNT = "count";
    
    static {
        ParserFactory.register(METHOD, ListParser.class, WallParser.class);
    }
    
    public GetWall() {
        super(METHOD);
    }

    public static class Builder {
        private GetWall request = new GetWall();

        public GetWall build() {
            return request;
        }

        public Builder setOwnerId(int ownerId) {
            request.add(TAG_OWNER_ID, Integer.toString(ownerId));
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
