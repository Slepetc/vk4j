package org.vk4j.parsers;

import org.vk4j.api.ParserBase;
import org.vk4j.api.ParserFactory;
import org.vk4j.requests.GetFriends;


import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: 28.04.2010
 * Time: 17:30:22
 */
public class FriendsListParser extends ParserBase {

    public static final String ID = GetFriends.METHOD;

    static {
        ParserFactory.register(LongParser.ID, LongParser.class);
    }

    public List<Long> parse(Object object) {
        return parseArray(object, LongParser.ID);
    }
}
