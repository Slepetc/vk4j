package org.vk4j.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.parsers.ListParser;
import org.vk4j.api.RequestBase;
import org.vk4j.parsers.VideoParser;

public class GetVideo extends RequestBase {

    public static final String METHOD = "video.get";
    
    public static final String TAG_VIDEOS = "videos";
    public static final String TAG_UID = "uid";
    public static final String TAG_WIDTH = "width"; // can be 130, 160 (default), 320
    public static final String TAG_COUNT = "count";
    public static final String TAG_OFFSET = "offset";

    static {
        ParserFactory.register(METHOD, ListParser.class, VideoParser.class);
    }

    public GetVideo() {
        super(METHOD);
    }
    
    public static class Builder {
        private GetVideo request = new GetVideo();

        public GetVideo build() {
            return request;
        }
        
        public static String buildVideo(int ownerId, int videoId) {
        	return Integer.toString(ownerId) + "_" + Integer.toString(videoId);
        }

        public Builder setVideos(String videos) {
            request.add(TAG_VIDEOS, videos);
            return this;
        }
    }        
}
