package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.api.RequestBase;
import org.vk4j.api.RequestExecutor;
import org.vk4j.parsers.ProfilesListParser;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: May 29, 2010
 * Time: 12:58:09 AM
 */
public class GetProfiles extends RequestBase {

    public static final String METHOD = "getProfiles";

    static {
        ParserFactory.register(METHOD, ProfilesListParser.class);
    }

    public GetProfiles(List<Long> uids) {
        add("method", METHOD);
        add("uids", createUidsString(uids));
    }

    private String createUidsString(List<Long> uids) {
        StringBuilder sb = new StringBuilder();

        for (Long uid : uids) {
            sb.append(uid).append(",");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

}
