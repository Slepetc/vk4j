package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.ListParser;
import org.vk4j.open.parsers.StringParser;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 10, 2010
 * Time: 4:33:20 PM
 */
public class GetAppFriends extends RequestBase {

    public static final String METHOD = "getAppFriends";

    static {
        ParserFactory.register(METHOD, ListParser.class, StringParser.class);
    }

    public GetAppFriends() {
        super(METHOD);
    }
}
