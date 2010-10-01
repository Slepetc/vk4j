package org.vk4j;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.vk4j.api.ParserFactory;
import org.vk4j.api.Request;
import org.vk4j.api.RequestExecutor;
import org.vk4j.connection.RedirectHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Vladimir Grachev.
 * Date: 28.04.2010
 * Time: 22:05:44
 */
public class Client implements RequestExecutor {

    private final AbstractHttpClient mHttpClient;
    private RedirectHandler redirectHandler;

    public static final String UTF8 = "utf-8";
//    private final IReloginHandler reloginHandler;

    /**
     * Default constructor
     */
    public Client() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 20 * 1000);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        mHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
        redirectHandler = new RedirectHandler();
        mHttpClient.setRedirectHandler(redirectHandler);
    }

    public void setCookieStore(CookieStore store) {
        mHttpClient.setCookieStore(store);
    }

    public CookieStore getCookieStore() {
        return mHttpClient.getCookieStore();
    }

    public <T> T execute(Request request) {
        T result = ParserFactory.<T>newParser(request.getMethod()).parse(process(request), this);
        if (!request.isPrimaryRequest())
            return result;
        if (!redirectHandler.isRelogin())
            return result;
        redirectHandler.setRelogin(false);
        return ParserFactory.<T>newParser(request.getMethod()).parse(process(request), this);
    }

    private String process(Request request) {

        try {
            return getPage(request.createHttpUriRequest());
        } catch (ClientProtocolException e) {
            //TODO: Internal Error
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            //TODO: Internal Error
            e.printStackTrace();
        } catch (IOException e) {
            //TODO: connection failed!
            e.printStackTrace();
        }
        return "";
    }

    private String getPage(HttpUriRequest request) throws IOException {
        return getPage(request, UTF8);
    }

    private String getPage(HttpUriRequest request, String charset) throws IOException {
        HttpResponse response = mHttpClient.execute(request);

        String page = getPage(response.getEntity().getContent(), request.getAllHeaders(), getCharset(response, charset));
        response.getEntity().consumeContent();

//        if (captchaHandler != null) {
//            while (captchaHandler.isCaptchaNeeded(page)){
//                ApiLog.log.e(TAG, "Captcha nedded");
//            }
//        }

        return page;
    }

    private String getPage(InputStream ios, Header[] headers, String charset) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(ios, Charset.forName(charset)));
        StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }

        br.close();

//        if (reloginHandler != null && reloginHandler.isReloginNeeded(sb.toString())){
//            return reloginHandler.onRelogin(sb.toString(), headers);
//        }

        return sb.toString();
    }

    public String getCharset(HttpResponse response, String defaultCharset) {
        Header contentType = response.getFirstHeader("Content-Type");
        if (contentType == null){
            return defaultCharset;
        }
        for (HeaderElement element : contentType.getElements()){
            NameValuePair charset = element.getParameterByName("charset");
            if (charset != null){
                return charset.getValue();
            }
        }
        return defaultCharset;
    }

}
