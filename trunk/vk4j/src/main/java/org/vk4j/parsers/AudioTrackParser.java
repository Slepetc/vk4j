package org.vk4j.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.vk4j.api.ParserBase;
import org.vk4j.api.VkException;
import org.vk4j.responses.AudioTrack;

/**
 * Created by Vladimir Grachev.
 * Date: 16.05.2010
 * Time: 22:50:49
 */
public class AudioTrackParser extends ParserBase<AudioTrack> {

    public static final String ID = "AudioTrack";

    public AudioTrack parse(Object object) {

        if (!(object instanceof JSONObject)) {
            throw new VkException("AudioTrackParser doesn't know how to parse not JSONObject");
        }

        JSONObject track = (JSONObject) object;

        try {
            Long aid = track.getLong("aid");
            String ownerId = track.getString("owner_id");
            String artist = track.getString("artist");
            String title = track.getString("title");
            String duration = track.getString("duration");
            String url = track.getString("url");

            return new AudioTrack(aid, ownerId, artist, title, duration, url);

        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

}
