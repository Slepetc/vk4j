package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.parsers.IntParser;
import org.vk4j.parsers.ListParser;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.MessageParser;

public class GetMessages extends RequestBase {

    public static final String METHOD = "messages.get";
    
    static {
        ParserFactory.register(METHOD, ListParser.class, MessageParser.class);
    }
    
    public GetMessages() {
        super(METHOD);
    }
}
