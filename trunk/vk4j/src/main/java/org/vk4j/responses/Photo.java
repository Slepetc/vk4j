package org.vk4j.responses;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Photo {

    public static final int PID = 0;
    public static final int AID = 1;
    public static final int OWNER_ID = 2;
    public static final int SRC = 3;
    public static final int SRC_SMALL = 4;
    public static final int SRC_BIG = 5;
    public static final int CREATED = 6;

    public static final String[] FIELDS = { "pid",
                                            "aid",
                                            "owner_id",
                                            "src",
                                            "src_small",
                                            "src_big",
                                            "created"
    };

    private final Map<String, String> values = new HashMap<String, String>();

    public Photo() {

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
    
    public Date getCreated() {
        if (!values.containsKey(FIELDS[CREATED])) {
            return null;
        }
        // vkontakte returns date stored in the similar format as java.sql.date
        // the only difference is that returned date has no milliseconds and therefore
        // must be multiplied by 1000
        try {
            return new Date(Long.parseLong(values.get(FIELDS[CREATED])) * 1000);
        }
        catch (NumberFormatException e) {
            return null;
        }    	
    }

    public long getPid() {
        if (!values.containsKey(FIELDS[PID])) {
            return 0L;
        }
        try {
            return Long.parseLong(values.get(FIELDS[PID]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }

    public long getAid() {
        if (!values.containsKey(FIELDS[AID])) {
            return 0L;
        }
        try {
            return Long.parseLong(values.get(FIELDS[AID]));
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Photo: ")
                .append(" {");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
