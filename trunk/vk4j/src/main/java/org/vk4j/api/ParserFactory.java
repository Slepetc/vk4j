package org.vk4j.api;

import org.vk4j.api.RequestBase;

import java.util.HashMap;

/**
 * Created by Vladimir Grachev.
 * Date: 07.05.2010
 * Time: 10:34:47
 */
public class ParserFactory {

    public static final HashMap<String, Class> types = new HashMap();

    public static void register(String method, Class parser) {
        types.put(method, parser);
    }

    public static Parser get(RequestBase request) {

        return create(types.get(request.getMethod()));
    }

    public static <T extends Parser> T  create(Class<T> type) {

        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();  
        }

        return null;
    }
}
