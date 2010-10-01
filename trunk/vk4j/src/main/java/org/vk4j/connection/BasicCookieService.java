package org.vk4j.connection;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vgrachev
 * Date: 06.03.2010
 * Time: 19:10:31
 * To change this template use File | Settings | File Templates.
 */
public class BasicCookieService implements ICookieService {

    private static final String TAG = "[webapi:BasicCookieService]";

    private String path = "";

	public BasicCookieService(){

	}

    public BasicCookieService(String path){
        this.path = path;
    }

    public void clearCookies(long profileId) {
        if (profileId == 0){
            return;
        }
	}

    public void storeCookies(long profileId, List<Cookie> cookies) {
        System.out.println("Save Cookies: " + path + profileId + ".cookies");

        try {
            FileOutputStream fout = new FileOutputStream(path + profileId + ".cookies");
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeInt(cookies.size());

            for (Cookie cookie : cookies){
                oos.writeObject(new SerializableCookie(cookie));
            }

            oos.close();
        } catch (IOException e) {
            System.out.println("storeCookie: IOException");
            e.printStackTrace();
        }

    }

    public List<Cookie> loadCookies(long profileId) {
//        CookieStore store = new BasicCookieStore();
//        if (profileId == 0){
//            return store;
//        }
        System.out.println("Load Cookies: " + path + profileId + ".cookies");

        List<Cookie> cookies = new ArrayList<Cookie>();

        try {
            FileInputStream fin = new FileInputStream(path + profileId + ".cookies");
            ObjectInputStream ois = new ObjectInputStream(fin);

            int count = ois.readInt();

            for (int i = 0; i < count; i++) {
                cookies.add(((SerializableCookie) ois.readObject()).getCookie());
//                store.addCookie(((SerializableCookie) ois.readObject()).getCookie());
            }

            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("loadCookies: file not found: " + path + profileId + ".cookies");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("loadCookie: IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("loadCookie: ClassNotFoundException");
            e.printStackTrace();
        }

        return cookies;
	}
}
