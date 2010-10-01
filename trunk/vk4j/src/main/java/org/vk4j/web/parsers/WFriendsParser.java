package org.vk4j.web.parsers;

import org.json.JSONArray;
import org.vk4j.api.Parser;
import org.vk4j.api.RequestExecutor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 13, 2010
 * Time: 7:30:43 PM
 */
public class WFriendsParser extends WParserBase<String> {

    public String parseWeb(String string, RequestExecutor executor) {
        return string;
    }
}
