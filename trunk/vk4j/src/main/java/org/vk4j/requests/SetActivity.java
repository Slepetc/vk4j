package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.parsers.IntParser;
import org.vk4j.api.RequestBase;

public class SetActivity extends RequestBase {

    public static final String METHOD = "activity.set";

    public static final String TAG_TEXT = "text";
    
    static {
        ParserFactory.register(METHOD, IntParser.class);
    }
    
    public SetActivity() {
        super(METHOD);
    }

    public static class Builder {
        private SetActivity request = new SetActivity();

        public SetActivity build() {
            return request;
        }

        public Builder setText(String text) {
            request.add(TAG_TEXT, text);
            return this;
        }
    }
}
