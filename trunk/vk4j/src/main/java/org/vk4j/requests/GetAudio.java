package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.AudioTrackListParser;

/**
 * Created by Vladimir Grachev.
 * Date: 16.05.2010
 * Time: 22:47:41
 */
public class GetAudio extends RequestBase {

    public static final String METHOD = "audio.get";

    static {
        ParserFactory.register(METHOD, AudioTrackListParser.class);
    }

    public GetAudio() {
        add("method", METHOD);
    }

//    public static class Parser extends ParserBase {
//
//        @Override
//        public AudioTracksList parse(JSONObject object) {
//            AudioTracksList result = new AudioTracksList();
//
//            try {
//                JSONArray array = object.getJSONArray("response");
//
//                AudioTrackParser parser = new AudioTrackParser();
//
//                for (int i = 0; i < array.length(); i++) {
//                    result.add(parser.parse(array.getJSONObject(i)));
//                }
//
//            } catch (JSONException e) {
//                //TODO: error!
//                e.printStackTrace();
//            }
//
//            return result;
//        }
//    }
}
