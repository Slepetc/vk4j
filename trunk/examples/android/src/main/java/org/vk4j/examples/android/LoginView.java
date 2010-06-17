package org.vk4j.examples.android;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.vk4j.Application;
import org.vk4j.api.VkException;
import org.vk4j.requests.GetFriends;

import java.util.List;

/**
 * Created by Vladimir Grachev.
 * Date: 26.04.2010
 * Time: 21:34:21
 */
public class LoginView extends Activity implements Application.ILoginProcessor {

    public static final String TAG = "[login:LoginView]";

    private String url = null;
    private WebView webview = null;
    private TextView textView = null;

    private Application app;

    private Application.ILoginVerifier loginVerifier = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.login);

            //TODO: You should define your application id in resource files as string
            //example: <string name="app_id">1234567</string>

            app = new Application(Long.parseLong(getString(R.string.app_id)));
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
                public void onPageFinished(WebView view, String url) {
                    Log.d(TAG, "onPageFinished: " + url);
                    if (loginVerifier.isLoginSuccess(url)) {
                        Log.d(TAG, "Login Success");
                        webview.setVisibility(View.INVISIBLE);

                        StringBuffer sb = new StringBuffer();

                        try {
                            List<String> friends = app.execute(new GetFriends());

                            for (String uid : friends) {
                                Log.d(TAG, "friendsList " + uid);
                                sb.append("friendId: ").append(uid).append("\n");
                            }

                            if (sb.length() > 0) {
                                sb.setLength(sb.length() - 1);
                            }

                            Log.d(TAG, sb.toString());

                            }
                        catch (VkException e) {
                            //TODO: temporary
                            sb.append("An error occurred while executing request.\n")
                                .append("This probably means that application was not approved by vk.com ")
                                .append("and only author can execute requests\n\n")
                                .append("Anyway be informed that vk4j library still in development stage, ")
                                .append("any questions are welcome at vladimir.grachev@gmail.com");
                        }
                        textView.setText(sb.toString());
                        textView.setVisibility(View.VISIBLE);
                    }
                    else if (url.contains("login_failure")) {
                        Log.d(TAG, "Login Failed");
                        webview.setVisibility(View.INVISIBLE);
                    }
                    else {
                        super.onPageFinished(view, url);
                    }
                }
            });

        app.login();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void login(String loginUrl) {
        webview.setVisibility(View.VISIBLE);
        webview.loadUrl(loginUrl);
    }

    public boolean login(String loginUrl, Application.ILoginVerifier verifier) {
        loginVerifier = verifier;
        webview.setVisibility(View.VISIBLE);
        webview.loadUrl(loginUrl);

        return false;
    }
}
