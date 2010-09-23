package org.vk4j.login;

import java.io.IOException;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 24, 2010
 * Time: 3:47:31 PM
 */
public interface LoginProcessor {
    public Session doLogin(String url, LoginResultListener resultListener) throws IOException;
}
