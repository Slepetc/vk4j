package org.vk4j.open;

import org.vk4j.connection.Client;
import org.vk4j.api.*;
import org.vk4j.open.login.LoginProcessor;
import org.vk4j.open.login.LoginResultListener;
import org.vk4j.open.login.Session;
import org.vk4j.open.requests.RequestBase;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 12:48:29
 */
public class Application implements LoginResultListener {

    private static final String TAG = "[vk4j:Application]";

    private final Client client;
    private Session session = null;
    private final long id;
    private String layout;
    private String type;
    private int settings;
    private final RequestDigest digest = new RequestDigest();
    private LoginProcessor loginProcessor;
    private LoginResultListener loginListener;

    public static final Pattern PATTERN_LOGIN = Pattern.compile(
            "login_success.*?session=(\\{.*?\\})"
    );

    private final static String LOGIN_URL = "http://vk.com/login.php?app=%d&layout=%s&type=%s&settings=%d";

    public Application(long id) {
        this(id, "touch", "browser", 126);
    }

    public Application(long id, String layout, String type, int settings){
        this.id = id;
        this.layout = layout;
        this.type = type;
        this.settings = settings;
        this.client = new Client();
    }

    public Application(long id, String layout, String type, int settings, Client client){
        this.id = id;
        this.layout = layout;
        this.type = type;
        this.settings = settings;
        this.client = client;
    }

    public String getLoginUrl() {
        return String.format(LOGIN_URL, id, layout, type, settings);
    }

    public boolean isLoggedIn() {
        return session != null;
    }
    
    @Deprecated
    public void setLoginProcessor(LoginProcessor processor) {
        this.loginProcessor = processor;
    }

    @Deprecated
    public void setLoginResultListener(LoginResultListener listener) {
        this.loginListener = listener;
    }

    @Deprecated
    public void onLoginResult(Session session) {
        setSession(session);
        if (loginListener != null) {
            loginListener.onLoginResult(session);    
        }
    }

    @Deprecated //LoginParser should be synchronize in application
    public void login() throws IOException {
        if (loginProcessor == null) {
            throw new VkException("LoginParser processor has not been initialized!");
        }
        loginProcessor.doLogin(getLoginUrl(), this);
    }

    public boolean loginSync(LoginProcessor processor) throws IOException {
        if (processor == null) {
            throw new VkException("LoginParser processor has not been initialized!");
        }

        setSession(processor.doLogin(getLoginUrl(), this));

        if (loginListener != null) {
            loginListener.onLoginResult(session);
        }

        return session != null;
    }


    public void setSession(Session session) {
        this.session = session;
    }

    public <S extends Object> S execute(RequestBase request) {
        prepare(request);

        // Special thanks to Oleg Ignatenko ( http://ru.linkedin.com/in/olegignatenko ) for code review and useful comments :)
        // see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6302954
        // and http://java.sun.com/docs/books/jls/third_edition/html/expressions.html#15.12
        return client.<S>execute(request);
    }

    private void prepare(RequestBase request) {
        request.add(RequestBase.TAG_API_ID, Long.toString(id));
        request.add(RequestBase.TAG_V, "3.0");
        request.add(RequestBase.TAG_FORMAT, "JSON");
//        request.add(Request.TAG_TEST_MODE, "1");

        request.add(RequestBase.TAG_SIG, digest.get(request));
        request.add(RequestBase.TAG_SID, session.getSid());
    }

    public class RequestDigest {

        private MessageDigest md5 = null;

        public RequestDigest() {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        public String get(RequestBase request) {
            StringBuilder sb = new StringBuilder();
            sb.append(session.getMid());
            md5.reset();
            for (Map.Entry<String, String> entry : request.getValues().entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue());
            }
            sb.append(session.getSecret());

            byte[] bytes = sb.toString().getBytes();
            md5.update(bytes, 0, bytes.length);


            BigInteger i = new BigInteger(1,md5.digest());
            return String.format("%1$032x", i);
        }
    }
}
