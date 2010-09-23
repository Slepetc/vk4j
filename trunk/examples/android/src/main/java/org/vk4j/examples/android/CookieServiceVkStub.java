package org.vk4j.examples.android;

import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import ru.spb.rook.vkdroid.webapi.cookie.ICookieService;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: Jun 24, 2010
 * Time: 5:54:48 PM
 */
public class CookieServiceVkStub implements ICookieService {

    public static final String TAG = "[CookieService]";

    private Context context;
    private String cookies;

    public CookieServiceVkStub(Context context) {
        this.context = context;
    }

    public CookieServiceVkStub(String cookies) {
        this.cookies = cookies;
    }

    public void storeCookies(long profileId, List<Cookie> cookies) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public CookieStore loadCookies(long profileId) {

        CookieStore store = new BasicCookieStore();
        if (context != null) {
            CookieSyncManager.createInstance(context);
            cookies = CookieManager.getInstance().getCookie("http://.vk.com/");
        }

        Log.d(TAG, "Load cookies " + cookies);

        for (String cookie : cookies.split(";")) {
            int idx = cookie.indexOf("=");
            Log.d(TAG, "Add cookie: " + cookie.substring(0, idx) + " - " + cookie.substring(idx+1));

            BasicClientCookie c = new BasicClientCookie(cookie.substring(0, idx), cookie.substring(idx+1));
            c.setDomain("vk.com");
            store.addCookie(c);
        }

        return store;
    }

    public void clearCookies(long profileId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
