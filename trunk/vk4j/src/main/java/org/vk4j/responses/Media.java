package org.vk4j.responses;

import java.util.HashMap;
import java.util.Map;

public class Media {
    public static final int TYPE = 0;
    public static final int ITEM_ID = 1;
    public static final int OWNER_ID = 2;
    public static final int THUMB_SRC = 3;
    public static final int APP_ID = 4;

    public static final String[] FIELDS = { "type",
                                            "item_id",
                                            "owner_id",
                                            "thumb_src",
                                            "app_id"
    };
    
    public enum MediaType {
    	unknown, app, graffiti, video, audio, photo, posted_photo
    }

    private final Map<String, String> values = new HashMap<String, String>();

    public Media() {

    }

    public void put(String field, String value) {
        values.put(field, value);
    }

    public String get(String field) {
        return values.get(field);
    }

    public String get(int field) {
        return values.get(FIELDS[field]);
    }
    
    public long getItemId() {
        if (!values.containsKey(FIELDS[ITEM_ID])) {
            return 0L;
        }
        try {
            return Long.parseLong(values.get(FIELDS[ITEM_ID]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }

    public int getOwnerId() {
        if (!values.containsKey(FIELDS[OWNER_ID])) {
            return 0;
        }
        try {
            return Integer.parseInt(values.get(FIELDS[OWNER_ID]));
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public Long getAppId() {
        if (!values.containsKey(FIELDS[APP_ID])) {
            return null;
        }
        try {
            return Long.parseLong(values.get(FIELDS[APP_ID]));
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
    
    public MediaType getMediaType() {
    	String type = values.get(FIELDS[TYPE]);
    	if (type != null)
    		try {
    			return MediaType.valueOf(type);
    		} catch (IllegalArgumentException e) {
    			return MediaType.unknown;
    		}
    	else
    		return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Media: ")
                .append(" {");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
