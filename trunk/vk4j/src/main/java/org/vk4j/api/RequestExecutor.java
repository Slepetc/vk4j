package org.vk4j.api;

/**
 * Created by Vladimir Grachev.
 * Date: 16.05.2010
 * Time: 22:09:19
 */
public interface RequestExecutor {
    public <S extends Object> S execute(Request request);
}
