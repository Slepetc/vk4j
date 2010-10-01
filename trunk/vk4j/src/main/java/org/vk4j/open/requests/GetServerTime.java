package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.IntParser;

public class GetServerTime extends RequestBase {

    public static final String METHOD = "getServerTime";

    static {
        ParserFactory.register(METHOD, IntParser.class);
    }

    public GetServerTime() {
        super(METHOD);
    }
}
