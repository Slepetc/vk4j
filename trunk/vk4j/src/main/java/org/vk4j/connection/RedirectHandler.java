package org.vk4j.connection;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

import java.net.MalformedURLException;
import java.net.URI;

/**
 * Created by Vladimir Grachev.
 * Date: 16.05.2010
 * Time: 20:51:32
 */
public class RedirectHandler extends DefaultRedirectHandler {

    private static String url = "";
    private boolean relogin = false;

    @Override
    public boolean isRedirectRequested(HttpResponse response, HttpContext context) {
        if (response.getHeaders("Location").length > 0)
            return true;
        return super.isRedirectRequested(response, context);
    }

    @Override
    public URI getLocationURI(HttpResponse response, HttpContext context) throws ProtocolException {

        URI uri = super.getLocationURI(response, context);

        try {
            url = uri.toURL().toString();
            if (url.contains("http://login.vk.com/?fast")) {
                relogin = true;    
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return uri;    
    }

    public static String getLastUrl(){
        return url;
    }

    public boolean isRelogin() {
        return relogin;
    }

    public void setRelogin(boolean relogin) {
        this.relogin = relogin;
    }
}
