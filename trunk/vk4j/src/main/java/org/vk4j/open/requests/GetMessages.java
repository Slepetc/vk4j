package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.ListParser;
import org.vk4j.open.parsers.MessageParser;

public class GetMessages extends RequestBase {

    public static final String METHOD = "messages.get";
    
    static {
        ParserFactory.register(METHOD, ListParser.class, MessageParser.class);
    }
    
    public GetMessages() {
        super(METHOD);
    }
}
