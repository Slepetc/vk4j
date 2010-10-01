package org.vk4j.web.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.vk4j.api.ParserFactory;
import org.vk4j.api.Request;
import org.vk4j.web.parsers.WLoginApplicationParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 30, 2010
 * Time: 5:05:38 PM
 */
public class WSaveApplicationSettingsRequest extends WRequestBase {

    public static final String METHOD = "web.application_save_settings";

    public static final String URL_SET_SETTINGS		= "http://vk.com/apps.php?act=a_save_settings";

    static {
        ParserFactory.register(METHOD, WLoginApplicationParser.class);
    }

    private String appId;
    private String appSettingsNeed;
    private String appSettingsHash;

    public WSaveApplicationSettingsRequest(String appId, String appSettingsNeed, String appSettingsHash) {
        this.appId = appId;
        this.appSettingsNeed = appSettingsNeed;
        this.appSettingsHash = appSettingsHash;
    }

    public WSaveApplicationSettingsRequest(String appId, String appSettingsNeed, String appSettingsHash, boolean primary) {
        super(primary);
        this.appId = appId;
        this.appSettingsNeed = appSettingsNeed;
        this.appSettingsHash = appSettingsHash;
    }

    public String getMethod() {
        return METHOD;
    }

    public String getUrl() {
        return URL_SET_SETTINGS;
    }

    public String getContent() {
        //TODO: this check should be in constructor;
        if (appSettingsNeed == null) {
            return "";
        }

        int appSettings = 0;

        try {
            appSettings = Integer.parseInt(appSettingsNeed);
        }
        catch (NumberFormatException e) {
        }

        if (appSettings == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 8192; i > 1; i = i >> 1) {
            if (i <= appSettings) {
                sb.append("app_settings_").append(i).append("=").append(i).append("&");
                appSettings -= i;
            }
        }

        sb.append("hash=").append(appSettingsHash).append("&id=").append(appId);

        return sb.toString();
    }

    public HttpUriRequest createHttpUriRequest() {
        HttpPost result = new HttpPost(getUrl());
        try {
            StringEntity entity = new StringEntity(getContent());
            entity.setContentType("application/x-www-form-urlencoded");
//            result.addHeader("X-Requested-With", "XMLHttpRequest");
            result.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            //TODO: throw vk exception?
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }
}
