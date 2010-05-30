package org.vk4j.api;

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

    public static Parser get(String id) {
        return create(types.get(id));
    }

    private static <T extends Parser> T  create(Class<T> type) {

        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            //TODO: 
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            //TODO:
            e.printStackTrace();  
        }

        return null;
    }
}
