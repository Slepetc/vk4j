package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.IntParser;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 10, 2010
 * Time: 4:52:58 PM
 */
public class GetUserBalance extends RequestBase {

    public static final String METHOD = "getUserBalance";

    static {
        ParserFactory.register(METHOD, IntParser.class);
    }

    public GetUserBalance() {
        super(METHOD);
    }
}
