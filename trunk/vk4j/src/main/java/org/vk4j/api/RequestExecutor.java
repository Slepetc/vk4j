package org.vk4j.api;

import org.vk4j.api.RequestBase;

/**
 * Created by Vladimir Grachev.
 * Date: 16.05.2010
 * Time: 22:09:19
 */
public interface RequestExecutor {
    public <T extends Request, S extends Object> S execute(T request);
}
