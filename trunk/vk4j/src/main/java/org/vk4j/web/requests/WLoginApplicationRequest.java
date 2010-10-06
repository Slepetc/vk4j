package org.vk4j.web.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.vk4j.api.ParserFactory;
import org.vk4j.web.parsers.WLoginApplicationParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 30, 2010
 * Time: 3:56:40 PM
 */
public class WLoginApplicationRequest extends WRequestBase {

    public static final String METHOD = "web.application_login";

    static {
        ParserFactory.register(METHOD, WLoginApplicationParser.class);
    }

    public static final String URL_LOGIN			= "http://vk.com/login.php";

    private String mUrl = URL_LOGIN;
    private String mApp = null;
    private String mHash = null;

    public WLoginApplicationRequest(String url) {
        mUrl = url;
    }

    public WLoginApplicationRequest(String url, boolean primary) {
        super(primary);
        mUrl = url;
    }

    public WLoginApplicationRequest(String app, String hash) {
        mApp = app;
        mHash = hash;
    }

    public WLoginApplicationRequest(String app, String hash, boolean primary) {
        super(primary);
        mApp = app;
        mHash = hash;
    }

    public String getMethod() {
        return METHOD;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getContent() {
        if (mApp == null || mHash == null) return "";

        StringBuffer sb = new StringBuffer();
        sb.append("act=a_auth")
            .append("&app=").append(mApp)
            .append("&hash=").append(mHash)
            .append("&permanent=1");
        return sb.toString();
    }

    public HttpUriRequest createHttpUriRequest() {
        HttpPost result = new HttpPost(getUrl());
        try {
            StringEntity entity = new StringEntity(getContent());
            entity.setContentType("application/x-www-form-urlencoded");
            result.addHeader("X-Requested-With", "XMLHttpRequest");
            result.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            //TODO: throw vk exception?
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }
}
