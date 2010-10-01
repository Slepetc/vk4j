package org.vk4j.web.parsers;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.vk4j.api.Parser;
import org.vk4j.api.RequestExecutor;
import org.vk4j.web.requests.WReloginRequest;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 26, 2010
 * Time: 6:26:12 PM
 */
public abstract class WParserBase<T extends Object> implements Parser<T> {

    private static final String TAG = "[webapi:BasicReloginHandler]";

    public T parse(String string, RequestExecutor executor) {
        if (WReloginRequest.isReloginNeeded(string)) {
            string = executor.execute(new WReloginRequest(string));
        }
        return parseWeb(string, executor);
    }

    public abstract T parseWeb(String string, RequestExecutor executor);

    public void setInnerType(String innerType) {
    }
}
