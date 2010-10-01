package org.vk4j.web.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.vk4j.api.ParserFactory;
import org.vk4j.api.Request;
import org.vk4j.web.parsers.WLoginSParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 13, 2010
 * Time: 7:18:35 PM
 */
public class WLoginSRequest extends WRequestBase {

    public static final String METHOD = "web.slogin";

    public static final String URL_SLOGIN = "http://vk.com/login.php";

    private final String mS;

    static {
        ParserFactory.register(METHOD, WLoginSParser.class);
    }

    public WLoginSRequest(String s) {
        mS = s;
    }

    public String getMethod() {
        return METHOD;
    }

    public String getUrl() {
        return URL_SLOGIN;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("op=slogin")
            .append("&s=").append(mS);
        return sb.toString();
    }

    public HttpUriRequest createHttpUriRequest() {
        HttpPost result = new HttpPost(getUrl());
        try {
            StringEntity entity = new StringEntity(getContent());
            entity.setContentType("application/x-www-form-urlencoded");
            result.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            //TODO: throw vk exception?
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }
}
