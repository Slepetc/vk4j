package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.IntParser;

public class GetServerTime extends RequestBase {

    public static final String METHOD = "getServerTime";

    static {
        ParserFactory.register(METHOD, IntParser.class);
    }

    public GetServerTime() {
        super(METHOD);
    }
}
