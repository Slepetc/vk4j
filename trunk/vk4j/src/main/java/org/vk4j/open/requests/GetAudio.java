package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.ListParser;
import org.vk4j.open.parsers.AudioTrackParser;

/**
 * Created by Vladimir Grachev.
 * Date: 16.05.2010
 * Time: 22:47:41
 */
public class GetAudio extends RequestBase {

    public static final String METHOD = "audio.get";

    static {
        ParserFactory.register(METHOD, ListParser.class, AudioTrackParser.class);
    }

    public GetAudio() {
        super(METHOD);
    }
}
