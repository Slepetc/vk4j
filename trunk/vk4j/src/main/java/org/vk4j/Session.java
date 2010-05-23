package org.vk4j;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 11:09:06
 */
public class Session {

    private static final String TAG = "[vk4j:Session]";

    private Long expire = null;
    private Long mid = null;
    private String secret = null;
    private String sid = null;

    public Session(Long expire, Long mid, String secret, String sid) {
        this.expire = expire;
        this.mid = mid;
        this.secret = secret;
        this.sid = sid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Session: {")
                .append("expire=").append(expire).append(", ")
                .append("mid=").append(mid).append(", ")
                .append("secret=").append(secret).append(", ")
                .append("sid=").append(sid)
                .append("}");
        return sb.toString();
    }

    public Long getMid() {
        return mid;
    }

    public String getSecret() {
        return secret;
    }

    public String getSid() {
        return sid;
    }
}
