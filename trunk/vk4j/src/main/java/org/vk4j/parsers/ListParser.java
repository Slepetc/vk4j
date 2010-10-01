package org.vk4j.parsers;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 9, 2010
 * Time: 3:16:41 PM
 */
public class ListParser<T extends Object> extends ParserBase<List<T>> {

    @Override
    public List<T> parse(Object object) {
        return parseArray(object, innerType);
    }
}
