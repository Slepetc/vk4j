package org.vk4j;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.vk4j.api.ParserFactory;
import org.vk4j.api.RequestBase;
import org.vk4j.api.Response;

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
class Client {

    private final AbstractHttpClient mHttpClient;

    private final static String API_URL = "http://api.vk.com/api.php";

    public Client() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 20 * 1000);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        mHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
    }

    private String process(RequestBase request) {

        try {

            HttpPost post = new HttpPost(API_URL);

            StringEntity entity = new StringEntity(request.toString());

            entity.setContentType("application/x-www-form-urlencoded");

            post.setEntity(entity);

            HttpResponse response = mHttpClient.execute(post);

            InputStream ios = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(ios, Charset.forName("windows-1251")));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            br.close();
            response.getEntity().consumeContent();

            return sb.toString();

        } catch (ClientProtocolException e) {
            //TODO: Internal Error
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            //TODO: Internal Error
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            //TODO: connection failed!
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    Response execute(RequestBase request) {
        return ParserFactory.get(request).parse(process(request));
    }

}
