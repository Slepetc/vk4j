package org.vk4j.responses;

/**
 * Created by Vladimir Grachev.
 * Date: 17.05.2010
 * Time: 0:08:59
 */
public class PhotoAlbum {

    private Long aid;
    private String title;
    private String description;

    public PhotoAlbum(long aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Photo Album: ")
                .append(" {")
                .append("aid=").append(aid)
                .append("}");
        return sb.toString();
    }
}
