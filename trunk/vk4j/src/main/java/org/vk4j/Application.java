package org.vk4j;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.RequestBase;
import org.vk4j.api.RequestExecutor;
import org.vk4j.api.Response;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 12:48:29
 */
public class Application implements RequestExecutor {

    private static final String TAG = "[vk4j:Application]";

    private final Client client;
    private Session session = null;
    private final long id;
    private String layout;
    private String type;
    private int settings;
    private final RequestDigest digest = new RequestDigest();
    private ILoginProcessor loginProcessor;

    public static final Pattern PATTERN_LOGIN = Pattern.compile(
            "login_success.*?session=(\\{.*?\\})"
    );

    private final static String LOGIN_URL = "http://vk.com/login.php?app=%d&layout=%s&type=%s&settings=%d";

    public Application(long id) {
        this(id, "touch", "browser", 10);
    }

    public Application(long id, String layout, String type, int settings){
        this.id = id;
        this.layout = layout;
        this.type = type;
        this.settings = settings;
        this.client = new Client();
    }

    public void setLoginProcessor(ILoginProcessor processor) {
        this.loginProcessor = processor;
    }

    ILoginVerifier verifier = new ILoginVerifier() {
        public boolean isLoginSuccess(String url) {
            return Application.this.isLoginSuccess(url);
        }
    };

    public boolean isLoginSuccess(String url) {
        try {
            Matcher m = PATTERN_LOGIN.matcher(URLDecoder.decode(url));

            if (m.find()) {
                JSONObject sessionInfo = new JSONObject(m.group(1));
                this.session = new Session(sessionInfo.getLong(Key.EXPIRE),
                                    sessionInfo.getLong(Key.MID),
                                    sessionInfo.getString(Key.SECRET),
                                    sessionInfo.getString(Key.SID));
                return true;
            }
        } catch (JSONException e) {
            //TODO: exception sender!
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return false;
    }

    public boolean login() {
        if (loginProcessor != null) {
            return loginProcessor.login(String.format(LOGIN_URL, id, layout, type, settings), verifier);
        }
        return false;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public <T extends RequestBase, S extends Response> S execute(T request) {
        prepare(request);

        return (S) client.execute(request);
    }


    private void prepare(RequestBase request) {
        request.add("api_id", Long.toString(id));
        request.add("v", "3.0");
        request.add("format", "JSON");
        request.add("test_mode", "1");

        request.add("sig", digest.get(request));
        request.add("sid", session.getSid());
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

    public interface ILoginProcessor {
        public boolean login(String loginUrl, ILoginVerifier verifier);
    }

    public interface ILoginVerifier {
        public boolean isLoginSuccess(String url);
    }

}
