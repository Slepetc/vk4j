package org.vk4j.web.parsers;

import org.vk4j.connection.RedirectHandler;
import org.vk4j.api.RequestExecutor;
import org.vk4j.open.login.LoginParserJson;
import org.vk4j.open.login.LoginParserUrl;
import org.vk4j.open.login.Session;
import org.vk4j.web.requests.WLoginApplicationRequest;
import org.vk4j.web.requests.WSaveApplicationSettingsRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 30, 2010
 * Time: 3:58:39 PM
 */
public class WLoginApplicationParser extends WParserBase<Session> {

    public static final Pattern PATTERN_APP     =
            Pattern.compile("var app_id = ([0-9]*?);.*?" +
                    "var app_settings_need = ([0-9]*?);.*?" +
                    "var app_settings_hash = '(.*?)';.*?" +
                    "var app_hash = '(.*?)';.*?" +
                    "var auth_hash = '(.*?)';", Pattern.DOTALL);

    public Session parseWeb(String string, RequestExecutor executor) {
        if (string.contains("Login success")) return new LoginParserUrl().parse(RedirectHandler.getLastUrl());
        if (string.contains("Login failed")) return new LoginParserUrl().parse(RedirectHandler.getLastUrl());
//        if (string.contains("sid")) return new LoginParserJson().parse(string);
        Session result = new LoginParserJson().parse(string);
        if (result != null) return result;

        Matcher m = PATTERN_APP.matcher(string);
        if (m.find()) {
            executor.execute(new WSaveApplicationSettingsRequest(m.group(1), m.group(2), m.group(3), false));
            return executor.execute(new WLoginApplicationRequest(m.group(1), m.group(5), false));
        }

        return null;
    }
}
