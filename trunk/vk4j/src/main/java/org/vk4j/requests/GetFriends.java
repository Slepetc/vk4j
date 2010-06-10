package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.parsers.ListParser;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.LongParser;
import org.vk4j.parsers.StringParser;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: 07.05.2010
 * Time: 10:36:53
 */
public class GetFriends extends RequestBase {

    public static final String METHOD = "getFriends";
    
    static {
        ParserFactory.register(METHOD, new Class[]{ListParser.class, StringParser.class});
    }
    
    public GetFriends() {
        add(TAG_METHOD, METHOD);
    }
}
