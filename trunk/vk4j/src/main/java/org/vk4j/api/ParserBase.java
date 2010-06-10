package org.vk4j.api;

import org.json.JSONArray;
import org.vk4j.api.VkException;

import java.util.List;


/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 23:50:48
 */
public abstract class ParserBase<T extends Object> implements Parser<T> {

    protected String innerType;

    public ParserBase () {
        this.innerType = null;
    }

    public void setInnerType(String innerType) {
        this.innerType = innerType;
    }

    public T parse(String string) {
        return Parser.Helper.<T>parse(string, this);
    }

    public abstract T parse(Object object);

    public <T1> List<T1> parseArray(Object object, String type) {

        if (!(object instanceof JSONArray)) {
            throw new VkException("ListParser can't parse not JSONArray object");
        }

        return Parser.Helper.<T1>parseArray((JSONArray) object, ParserFactory.<T1>newParser(type));
    }

    public T getFromArray(JSONArray array, int idx) {
        return Parser.Helper.<T>getFromArray(array, idx, this);
    }
}
