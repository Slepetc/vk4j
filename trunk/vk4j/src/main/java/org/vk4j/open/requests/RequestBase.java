package org.vk4j.open.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.vk4j.api.Request;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 18:18:12
 */
public abstract class RequestBase implements Request {

    private final static String API_URL = "http://api.vk.com/api.php";

    public static final String TAG_METHOD = "method";
    public static final String TAG_API_ID = "api_id";
    public static final String TAG_V = "v";
    public static final String TAG_FORMAT = "format";
    public static final String TAG_TEST_MODE = "test_mode";
    public static final String TAG_SIG = "sig";
    public static final String TAG_SID = "sid";

    private TreeMap<String, String> values = new TreeMap<String, String>();

    public RequestBase(String method) {
        values.put(TAG_METHOD, method);
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void add(String key, String value) {
        values.put(key, value);
    }

    public void add(String key, List<String> values) {
        StringBuilder sb = new StringBuilder();

        for (String value : values) {
            sb.append(value).append(",");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        add(key, sb.toString());
    }

    public void add(String key, String[] values) {
        StringBuilder sb = new StringBuilder();

        for (String value : values) {
            sb.append(value).append(",");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        add(key, sb.toString());
    }

    public String getMethod() {
        return values.get(TAG_METHOD);
    }

    public String getUrl() {
        return API_URL;
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.setLength(sb.length() - 1);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
