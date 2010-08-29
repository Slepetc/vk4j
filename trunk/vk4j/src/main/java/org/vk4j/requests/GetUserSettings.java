package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.IntParser;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 10, 2010
 * Time: 5:23:18 PM
 */
public class GetUserSettings extends RequestBase {

    public static final String METHOD = "getUserSettings";

    static {
        ParserFactory.register(METHOD, IntParser.class);
    }

    public GetUserSettings() {
        super(METHOD);
    }
}
