package org.vk4j.open.responses;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Video {

    public static final int VID = 0;
    public static final int OWNER_ID = 1;
    public static final int TITLE = 2;
    public static final int DESCRIPTION = 3;
    public static final int DURATION = 4;
    public static final int LINK = 5;
    public static final int IMAGE = 6;
    public static final int DATE = 7;

    public static final String[] FIELDS = { "vid",
                                            "owner_id",
                                            "title",
                                            "description",
                                            "duration",
                                            "link",
                                            "image",
                                            "date"
    };

    private final Map<String, String> values = new HashMap<String, String>();

    public Video() {

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
    
    public Date getDate() {
        if (!values.containsKey(FIELDS[DATE])) {
            return null;
        }
        try {
            return new Date(Long.parseLong(values.get(FIELDS[DATE])) * 1000);
        }
        catch (NumberFormatException e) {
            return null;
        }    	
    }

    public long getVid() {
        if (!values.containsKey(FIELDS[VID])) {
            return 0L;
        }
        try {
            return Long.parseLong(values.get(FIELDS[VID]));
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
    
    public long getDuration() {
        if (!values.containsKey(FIELDS[DURATION])) {
            return 0L;
        }
        try {
            return Long.parseLong(values.get(FIELDS[DURATION]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Video: ")
                .append(" {");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
