package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.AudioTrackListParser;

/**
 * Created by Vladimir Grachev.
 * Date: 16.05.2010
 * Time: 22:47:41
 */
public class GetAudio extends RequestBase {

    public static final String METHOD = "audio.get";

    static {
        ParserFactory.register(METHOD, AudioTrackListParser.class);
    }

    public GetAudio() {
        add("method", METHOD);
    }
}
