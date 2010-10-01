/**
 * 
 */
package org.vk4j.connection;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

import java.util.List;

/**
 * @author vgrachev
 *
 */
public interface ICookieService {

	public void storeCookies(long profileId, List<Cookie> cookies);

	public List<Cookie> loadCookies(long profileId);

	public void clearCookies(long profileId);
}
