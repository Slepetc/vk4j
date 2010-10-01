package org.vk4j.open.requests;

import org.vk4j.api.ParserFactory;
import org.vk4j.open.parsers.ListParser;
import org.vk4j.open.parsers.PhotoParser;

public class GetPhotosById extends RequestBase {

    public static final String METHOD = "photos.getById";
    
    public static final String TAG_PHOTOS = "photos";

    static {
        ParserFactory.register(METHOD, ListParser.class, PhotoParser.class);
    }

    public GetPhotosById() {
        super(METHOD);
    }
    
    public static class Builder {
        private GetPhotosById request = new GetPhotosById();

        public GetPhotosById build() {
            return request;
        }
        
        public static String buildPhoto(int ownerId, int photoId) {
        	return Integer.toString(ownerId) + "_" + Integer.toString(photoId);
        }

        public Builder setPhotos(String photos) {
            request.add(TAG_PHOTOS, photos);
            return this;
        }
    }        
}
