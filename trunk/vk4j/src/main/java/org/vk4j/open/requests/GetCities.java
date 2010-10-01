package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.CitiesParser;
import org.vk4j.open.responses.Profile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vladimir Grachev.
 * Date: Jul 5, 2010
 * Time: 7:47:53 PM
 */
public class GetCities extends RequestBase {

    public static final String METHOD = "getCities";

    public static final String TAG_CIDS = "cids";

    static {
        ParserFactory.register(METHOD, CitiesParser.class);
    }

    public GetCities() {
        super(METHOD);
    }

    public static class Builder {
        private GetCities request = new GetCities();

        public GetCities build() {
            return request;
        }

        public Builder setCids(String cids) {
            request.add(TAG_CIDS, cids);
            return this;
        }

        public Builder setCids(List<Profile> uids) {

            Set<String> cids = new HashSet<String>();

            for (Profile uid : uids) {
                if (uid == null) {
                    continue;
                }
                String cid = (uid.get(Profile.CITY));
                if (cid == null) {
                    continue;
                }
                cids.add(cid);
            }

            StringBuffer sb = new StringBuffer();
            for (String cid : cids) {
                sb.append(cid).append(",");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            setCids(sb.toString());
            return this;
        }
    }
}
