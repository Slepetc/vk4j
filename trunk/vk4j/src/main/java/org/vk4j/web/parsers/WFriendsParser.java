package org.vk4j.web.parsers;

import org.json.JSONArray;
import org.vk4j.api.Parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 13, 2010
 * Time: 7:30:43 PM
 */
public class WFriendsParser implements Parser<String> {

    public String parse(String string) {
        return string;
    }

    public String parse(Object object) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T1 extends Object> List<T1> parseArray(Object object, String type) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getFromArray(JSONArray array, int idx) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setInnerType(String innerType) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
