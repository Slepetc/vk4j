package org.vk4j.web.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.vk4j.api.ParserFactory;
import org.vk4j.api.Request;
import org.vk4j.web.parsers.WLoginParser;
import org.vk4j.web.parsers.WReloginParser;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 30, 2010
 * Time: 6:25:43 PM
 */
public class WReloginRequest extends WRequestBase {
    public static final String METHOD = "web.relogin";


    static {
        ParserFactory.register(METHOD, WReloginParser.class);
    }

    public String mUrl;
    public String mEntity;

    public static final Pattern PATTERN_F_FORM =
            Pattern.compile("<form id=\"f\" name=\"f\" method=\"post\" action=\"(.*?)\">" +
                    "(.*?)" +
                    "</form>",
                    Pattern.DOTALL);

    public static final Pattern PATTERN_F_FORM_FIELDS =
            Pattern.compile(
                    "<input type='hidden' name='(.*?)' value='(.*?)' />",
                    Pattern.DOTALL
            );

    public static final String RELOGIN_NEEDED = "setTimeout(\"location.href='http://vk.com/login.php?op=logout'\"";

    public static boolean isReloginNeeded(String page) {
        return page.contains(RELOGIN_NEEDED);    
    }

    public WReloginRequest(String page) {
        Matcher m = PATTERN_F_FORM.matcher(page);

        if (m.find()) {

            String url = m.group(1);
            String form = m.group(2);

            Matcher m2 = PATTERN_F_FORM_FIELDS.matcher(form);

            StringBuilder sb = new StringBuilder();

            while (m2.find()){
                sb.append(m2.group(1)).append("=").append(m2.group(2)).append("&");
            }

            mEntity = sb.toString();
        }
    }

    public String getMethod() {
        return METHOD;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getContent() {
        return mEntity;
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
