package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.FriendsListParser;
import org.vk4j.parsers.ListParser;
import org.vk4j.parsers.LongParser;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: 07.05.2010
 * Time: 10:36:53
 */
public class GetFriends extends RequestBase {

    public static final String METHOD = "getFriends";

    static {
        ParserFactory.register(METHOD, FriendsListParser.class);
    }
    
    public GetFriends() {
        add("method", METHOD);
    }

}
