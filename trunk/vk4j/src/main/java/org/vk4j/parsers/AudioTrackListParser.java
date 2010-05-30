package org.vk4j.parsers;

import org.vk4j.api.ParserBase;
import org.vk4j.api.ParserFactory;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: May 29, 2010
 * Time: 1:33:13 AM
 */
public class AudioTrackListParser extends ParserBase {

    static {
        ParserFactory.register(AudioTrackParser.ID, AudioTrackParser.class);
    }

    public List<Long> parse(Object object) {
        return parseArray(object, AudioTrackParser.ID);
    }

}

