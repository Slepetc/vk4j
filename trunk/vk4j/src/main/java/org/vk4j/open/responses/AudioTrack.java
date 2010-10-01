package org.vk4j.open.responses;

/**
 * Created by Vladimir Grachev.
 * Date: 16.05.2010
 * Time: 22:41:17
 */
public class AudioTrack {

    private Long aid;
    private String ownerId;
    private String artist;
    private String title;
    private String duration;
    private String url;

    public AudioTrack(Long aid) {
        this.aid = aid;
    }

    public AudioTrack(Long aid, String ownerId, String artist, String title, String duration, String url) {
        this.aid = aid;
        this.ownerId = ownerId;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Audio Track: ")
                .append(" {")
                .append("aid=").append(aid).append(", ")
                .append("ownerId=").append(ownerId).append(", ")
                .append("artist=").append(artist).append(", ")
                .append("title=").append(title).append(", ")
                .append("duration=").append(duration).append(", ")
                .append("url=").append(url)
                .append("}");
        return sb.toString();
    }

}
