package org.vk4j.web.parsers;

import org.vk4j.api.Parser;
import org.vk4j.api.RequestExecutor;

/**
 * Created by Vladimir Grachev.
 * Date: Sep 30, 2010
 * Time: 8:23:09 PM
 */
public class WReloginParser implements Parser<String> {

    public String parse(String string, RequestExecutor executor) {
         return string;
    }

    public void setInnerType(String innerType) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
