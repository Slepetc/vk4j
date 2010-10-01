package org.vk4j.connection;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: vgrachev
 * Date: 09.04.2010
 * Time: 16:25:53
 * To change this template use File | Settings | File Templates.
 */
public class SerializableCookie implements Serializable {

    private static final long serialVersionUID = -3459216252494411718L;

    private String name;
    private String value;
    private String domain;
    private Date expiryDate;
    private String path;

    public SerializableCookie(Cookie cookie) {
        name 	= cookie.getName();
        value 	= cookie.getValue();
//    		if (scookie.name.equals("remixlang")){
//    			scookie.value = "3";//English
//    		}
        domain	= cookie.getDomain();
        expiryDate = cookie.getExpiryDate();
        path	= cookie.getPath();
    }

    public Cookie getCookie() {
        BasicClientCookie cookie = new BasicClientCookie(name, value);

        cookie.setDomain(domain);
        cookie.setExpiryDate(expiryDate);
        cookie.setPath(path);

        return cookie;
    }
}
