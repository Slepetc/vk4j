package org.vk4j.web.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.vk4j.api.ParserFactory;
import org.vk4j.api.Request;
import org.vk4j.web.parsers.WFriendsParser;
import org.vk4j.web.parsers.WLoginParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 13, 2010
 * Time: 7:29:57 PM
 */
public class WFriendsRequest extends WRequestBase {

    public static final String METHOD = "web.friends";

    public static final String URL_FRIENDS_AJAX 	= "http://vk.com/friends_ajax.php";

    private String mUid;

    static {
        ParserFactory.register(METHOD, WFriendsParser.class);
    }


    public String getMethod() {
        return METHOD;
    }

    public String getUrl() {
        return URL_FRIENDS_AJAX;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public WFriendsRequest(String uid) {
        mUid = uid;
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(mUid)
            .append("&filter=").append("phonebook");
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
