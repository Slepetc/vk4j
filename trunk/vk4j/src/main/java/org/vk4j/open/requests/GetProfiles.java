package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.ListParser;
import org.vk4j.open.parsers.ProfileParser;

import java.util.Collection;

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
        super(METHOD);
    }

    public static class Builder {
        private GetProfiles request = new GetProfiles(); 

        public GetProfiles build() {
            return request;
        }

        public Builder setUids(String uids) {
            request.add(TAG_UIDS, uids);
            return this;
        }

        public Builder setUids(Collection uids) {
            StringBuffer sb = new StringBuffer();
            for (Object uid : uids) {
                sb.append(uid).append(",");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            setUids(sb.toString());
            return this;
        }

        public Builder setFields(String fields) {
            request.add(TAG_FIELDS, fields);
            return this;
        }
    }
}
