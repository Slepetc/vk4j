package org.vk4j.api;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: 07.05.2010
 * Time: 10:34:47
 */
public class ParserFactory {

    public static final HashMap<String, Class> types = new HashMap();

    private ParserFactory() {} // IMPL NOTE instantiation prohibited

    public static void register(String method, Class... parsers) {
        for (Class parser : parsers) {
            types.put(method, parser);
            method += "$";
        }
    }


    public static<T> Parser<T> newParser(String id) {
        if (id == null) {
            throw new VkException("Illegal id == null for parser creation");
        }
        return newParser(types.get(id), id + "$");
    }

    private static <T extends Parser> T newParser(Class<T> type, String innerId) {

        try {
            T result = type.newInstance();
            result.setInnerType(innerId);
            return result;
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
