package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.ListParser;
import org.vk4j.open.parsers.StringParser;

/**
 * Created by Vladimir Grachev.
 * Date: 07.05.2010
 * Time: 10:36:53
 */
public class GetFriends extends RequestBase {

    public static final String METHOD = "getFriends";
    
    static {
        ParserFactory.register(METHOD, ListParser.class, StringParser.class);
    }
    
    public GetFriends() {
        super(METHOD);
    }
}
