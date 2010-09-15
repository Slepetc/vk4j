package org.vk4j.examples.android;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.vk4j.Application;
import org.vk4j.login.LoginParserJson;
import org.vk4j.login.LoginParserUrl;
import org.vk4j.login.LoginProcessor;
import org.vk4j.login.LoginResultListener;
import org.vk4j.login.Session;
import org.vk4j.requests.GetFriends;
import ru.spb.rook.vkdroid.webapi.WebSession;
import ru.spb.rook.vkdroid.webapi.parsers.AppLogin;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: 26.04.2010
 * Time: 21:34:21
 */
public class LoginView extends Activity implements LoginProcessor {

    public static final String TAG = "[login:LoginView]";

    private String url = null;
    private WebView webview = null;
    private TextView textView = null;

    private Application app;
    private LoginResultListener resultListener;

    private CookieServiceVkStub cookieService;

//    private Application.ILoginVerifier loginVerifier = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.login);

            //TODO: You should define your application id in resource files as string
            //example: <string name="app_id">1234567</string>

            app = new Application(Long.parseLong(getString(R.string.app_id)), "touch", "browser", 10);
            app.setLoginProcessor(this);


            textView = (TextView)findViewById(R.id.result);
            webview = (WebView)findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);
            final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
            progressBar.setVisibility(View.GONE);

            final Handler handler = new Handler();

            webview.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {

                    progressBar.setProgress(progress);
                    if (progress == 100) {
                        handler.post(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            });

            webview.setWebViewClient(new WebViewClient() {

                

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {

                    Log.d(TAG, "onPageStarted: " + url);

                    super.onPageStarted(view, url, favicon);    //To change body of overridden methods use File | Settings | File Templates.
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    Log.d(TAG, "onPageFinished: " + url);
                    Log.d(TAG, "Has cookies: " + CookieManager.getInstance().hasCookies());

                    Log.d(TAG, "Cookie: " + CookieManager.getInstance().getCookie(url));

                    cookieService = new CookieServiceVkStub(CookieManager.getInstance().getCookie(url));

                    try {
                        customRequest(url);
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    resultListener.onLoginResult(new LoginParserUrl().parse(url));

                    autoLogin();

//                    if (loginVerifier.isLoginSuccess(url)) {
//                        Log.d(TAG, "Login Success");
//                        webview.setVisibility(View.INVISIBLE);
//
//                        StringBuffer sb = new StringBuffer();
//
//                        try {
//                            List<String> friends = app.execute(new GetFriends());
//
//                            for (String uid : friends) {
//                                Log.d(TAG, "friendsList " + uid);
//                                sb.append("friendId: ").append(uid).append("\n");
//                            }
//
//                            if (sb.length() > 0) {
//                                sb.setLength(sb.length() - 1);
//                            }
//
//                            Log.d(TAG, sb.toString());
//
//                            }
//                        catch (VkException e) {
//                            //TODO: temporary
//                            sb.append("An error occurred while executing request.\n")
//                                .append("This probably means that application was not approved by vk.com ")
//                                .append("and only author can execute requests\n\n")
//                                .append("Anyway be informed that vk4j library still in development stage, ")
//                                .append("any questions are welcome at vladimir.grachev@gmail.com");
//                        }
//                        textView.setText(sb.toString());
//                        textView.setVisibility(View.VISIBLE);
//                    }
//                    else if (url.contains("login_failure")) {
//                        Log.d(TAG, "Login Failed");
//                        webview.setVisibility(View.INVISIBLE);
//                    }
//                    else {
//                        super.onPageFinished(view, url);
//                    }
                }
            });

        app.login();
    }

    private void autoLogin() {
        new Thread(new Runnable(){

            public void run() {

                LoginResultListener listener = new LoginResultListener() {
                    public void onLoginResult(Session session) {
                        Log.d(TAG, "onLoginResult " + session);
                        if (session != null) {
                            Log.d(TAG, "Logged in " + session.toString());
                            List<String> friends = app.execute(new GetFriends());

                            StringBuffer sb = new StringBuffer();

                            for (String uid : friends) {
                                Log.d(TAG, "friendsList " + uid);
                                sb.append("friendId: ").append(uid).append("\n");
                            }

                            if (sb.length() > 0) {
                                sb.setLength(sb.length() - 1);
                            }

                            Log.d(TAG, sb.toString());
                        }
                    }
                };

                LoginProcessor processor = new LoginProcessor() {

                    public final Pattern PATTERN_APP     =
                            Pattern.compile("var app_id = ([0-9]*?);.*?" +
                                    "var app_hash = '(.*?)';.*?" +
                                    "var auth_hash = '(.*?)';", Pattern.DOTALL);

                    public void doLogin(String url, LoginResultListener resultListener) {
                        AppLogin login = new AppLogin(new WebSession(cookieService, 12));
                        resultListener.onLoginResult(new LoginParserJson().parse(login.login(url)));
                    }
                };


                app.setLoginProcessor(processor);
                app.setLoginResultListener(listener);

                app.login();
            }
        }).start();
    }


    private void customRequest(String url) throws IOException {
//        HttpParams params = new BasicHttpParams();
////        HttpConnectionManagerParams.setMaxTotalConnections(params, 100);
//        HttpConnectionParams.setConnectionTimeout(params, 20 * 1000);
//        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//        params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
//
//        SchemeRegistry schemeRegistry = new SchemeRegistry();
//        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
////        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
//
//        DefaultHttpClient mHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
//
//        HttpGet request = new HttpGet("http://vk.com/friends.php");
//
//        request.addHeader("Cookie", CookieManager.getInstance().getCookie(url));
//        HttpResponse response = mHttpClient.execute(request);
//
//        InputStream ios = response.getEntity().getContent();
//        BufferedReader br = new BufferedReader(new InputStreamReader(ios, Charset.forName("windows-1251")));
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//
//        while ((line = br.readLine()) != null) {
//            sb.append(line + "\n");
//        }
//
//        br.close();
//        response.getEntity().consumeContent();
//
////        Log.d(TAG, sb.toString());
//
//        try {
//            FriendsInfo info = FriendsInfo.fromWeb(sb.toString());
//            if (info != null) {
//                Log.d(TAG, info.toString());
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
////        mHttpClient.getParams().setParameter(
////                ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
//
////        CookieStore mCookieStore = new BasicCookieStore();
////        mCookieStore.addCookie();
////
////        mHttpClient.setCookieStore(mCookieStore);



    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void login(String loginUrl) {
        webview.setVisibility(View.VISIBLE);
//        webview.loadUrl(loginUrl);
    }

//    public boolean login(String loginUrl, Application.ILoginVerifier verifier) {
//        loginVerifier = verifier;
//        webview.setVisibility(View.VISIBLE);
////        webview.loadUrl(loginUrl);
//
//        return false;
//    }

    public void doLogin(String url, LoginResultListener resultListener) {
        this.resultListener = resultListener;
        webview.setVisibility(View.VISIBLE);
        webview.loadUrl(url);
    }
}


//-------
//                            HttpParams params = new BasicHttpParams();
//                            HttpConnectionParams.setConnectionTimeout(params, 20 * 1000);
//                            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//                            params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
//
//                            SchemeRegistry schemeRegistry = new SchemeRegistry();
//                            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//
//                            DefaultHttpClient mHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
//
//
//                            CookieSyncManager.createInstance(LoginView.this);
//                            //---------------------------------------------------
//                            HttpGet get = new HttpGet(url);
//                            get.addHeader("Cookie", CookieManager.getInstance().getCookie(url));
//                            HttpResponse response = mHttpClient.execute(get);
//
//                            InputStream ios = response.getEntity().getContent();
//                            BufferedReader br = new BufferedReader(new InputStreamReader(ios, Charset.forName("windows-1251")));
//                            StringBuilder sb = new StringBuilder();
//                            String line = null;
//
//                            while ((line = br.readLine()) != null) {
//                                sb.append(line + "\n");
//                            }
//
//                            br.close();
//
//                            Matcher m = PATTERN_APP.matcher(sb.toString());
//
//                            String app_id = null;
//                            String app_hash = null;
//                            String auth_hash = null;
//
//                            if (m.find()) {
//                                app_id = m.group(1);
//                                app_hash = m.group(2);
//                                auth_hash = m.group(3);
//                            }
//
//
//                            //--------------------------------------------------
//                            HttpPost request = new HttpPost("http://vk.com/login.php");
//                            request.addHeader("Cookie", CookieManager.getInstance().getCookie("http://vk.com/login.php"));
//                            StringEntity entity = new StringEntity(
//                                                                "act=a_auth" +
//                                                                "&app=" + app_id +
//                                                                "&hash=" + auth_hash +
//                                                                "&permanent=1");
//                            entity.setContentType("application/x-www-form-urlencoded");
//                            request.addHeader("X-Requested-With", "XMLHttpRequest");
//                            request.setEntity(entity);
//
//
//                            request.addHeader("Cookie", CookieManager.getInstance().getCookie(url));
//                            response = mHttpClient.execute(request);
//
//                            ios = response.getEntity().getContent();
//                            br = new BufferedReader(new InputStreamReader(ios, Charset.forName("windows-1251")));
//                            sb = new StringBuilder();
//                            line = null;
//
//                            while ((line = br.readLine()) != null) {
//                                sb.append(line + "\n");
//                            }
//
//                            br.close();
//                            response.getEntity().consumeContent();
//
//                            resultListener.onLoginResult(new LoginParserJson().parse(sb.toString()));
//
//
//                        } catch (ClientProtocolException e) {
//                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                        } catch (IOException e) {
//                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                        }


