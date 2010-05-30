package org.vk4j.parsers;

import org.vk4j.api.ParserBase;
import org.vk4j.api.ParserFactory;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: May 29, 2010
 * Time: 1:00:50 AM
 */
public class ProfilesListParser extends ParserBase {

    static {
        ParserFactory.register(ProfileParser.ID, ProfileParser.class);
    }

    public List<Long> parse(Object object) {
        return parseArray(object, ProfileParser.ID);
    }

}
