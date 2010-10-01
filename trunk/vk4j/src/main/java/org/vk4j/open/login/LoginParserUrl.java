package org.vk4j.open.login;

import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 24, 2010
 * Time: 2:51:34 PM
 */
public class LoginParserUrl implements LoginParser {

    public static final Pattern PATTERN_LOGIN = Pattern.compile(
            "login_success.*?session=(\\{.*?\\})"
    );

    public Session parse(String url) {
        Matcher m = PATTERN_LOGIN.matcher(URLDecoder.decode(url));

        if (m.find()) {
            return new LoginParserJson().parse(m.group(1));
        }
        //TODO: Temporary
        return new LoginParserJson().parse(url);
    }

}
