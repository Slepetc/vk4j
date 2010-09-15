package org.vk4j.web.parsers;

import org.json.JSONArray;
import org.vk4j.api.Parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 13, 2010
 * Time: 5:19:29 PM
 */
public class WLoginParser implements Parser<String> {

    public static final Pattern PATTERN_S 		=
            Pattern.compile("type='hidden' name='s'.*?value='(.*?)'");

    private static String S_FAILED = "nonenone";
    
    public String parse(String string) {
        Matcher m = PATTERN_S.matcher(string);

        String s = null;

        while (m.find()) {
            s = m.group(1);
        }

        if (S_FAILED.equals(s)) {
            return null;
        }
        if (s == null){
            throw new RuntimeException("SLogin failed!!! Probably response does not match pattern");
        }

        return s;
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
