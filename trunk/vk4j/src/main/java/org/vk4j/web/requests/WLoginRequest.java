package org.vk4j.web.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.vk4j.api.ParserFactory;
import org.vk4j.web.parsers.WLoginParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 12, 2010
 * Time: 8:20:31 PM
 */
public class WLoginRequest extends WRequestBase {

    public static final String METHOD = "web.login";

    public static final String URL_LOGIN 	= "http://login.vk.com/";

    private String mUsername;
    private String mPassword;

    static {
        ParserFactory.register(METHOD, WLoginParser.class);
    }

    public WLoginRequest(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public String getMethod() {
        return METHOD;
    }

    public String getUrl() {
        return URL_LOGIN;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("act=login")
            .append("&email=").append(mUsername)
            .append("&pass=").append(mPassword)
            .append("&expire=")
            .append("&vk=1");
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
