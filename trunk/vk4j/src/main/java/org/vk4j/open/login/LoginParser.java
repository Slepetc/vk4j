package org.vk4j.open.login;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 24, 2010
 * Time: 2:53:47 PM
 */
public interface LoginParser {

    public static final String KEY_MID = "mid";
    public static final String KEY_SID = "sid";
    public static final String KEY_SECRET = "secret";
    public static final String KEY_EXPIRE = "expire";
    
    public Session parse(String string);
    
}
