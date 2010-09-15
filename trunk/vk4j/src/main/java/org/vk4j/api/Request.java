package org.vk4j.api;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by Vladimir Grachev.
 * Date: May 23, 2010
 * Time: 5:23:24 PM
 */
public interface Request {
//    public static final String TAG_METHOD = "method";
//    public static final String TAG_API_ID = "api_id";
//    public static final String TAG_V = "v";
//    public static final String TAG_FORMAT = "format";
//    public static final String TAG_TEST_MODE = "test_mode";
//    public static final String TAG_SIG = "sig";
//    public static final String TAG_SID = "sid";
//
//    public Map<String, String> getValues();
//    public void add(String key, String value);
//    public void add(String key, List<String> values);
    public String getMethod();

    public String getUrl();

    public String getContent();

    public HttpUriRequest createHttpUriRequest();    
}
