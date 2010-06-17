package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.parsers.ListParser;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.ProfileParser;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: May 29, 2010
 * Time: 12:58:09 AM
 */
public class GetProfiles extends RequestBase {

    public static final String METHOD = "getProfiles";

    public static final String TAG_UIDS = "uids";
    public static final String TAG_FIELDS = "fields";
    public static final String TAG_NAME_CASE = "name_case";

    static {
        ParserFactory.register(METHOD, ListParser.class, ProfileParser.class);
    }

    public GetProfiles() {
        add(TAG_METHOD, METHOD);
    }
}
