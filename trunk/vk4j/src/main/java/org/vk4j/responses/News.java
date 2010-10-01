package org.vk4j.responses;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class News {

    public static final int UID = 0;
    public static final int TEXT = 1;
    public static final int TIMESTAMP = 2;

    public static final String[] FIELDS = { "uid",
                                            "text",
                                            "timestamp"
    };

    private final Map<String, String> values = new HashMap<String, String>();

    public News() {

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
    
    public Date getTimeStamp() {
        if (!values.containsKey(FIELDS[TIMESTAMP])) {
            return null;
        }
        // vkontakte returns date stored in the similar format as java.sql.date
        // the only difference is that returned date has no milliseconds and therefore
        // must be multiplied by 1000
        try {
            return new Date(Long.parseLong(values.get(FIELDS[TIMESTAMP])) * 1000);
        }
        catch (NumberFormatException e) {
            return null;
        }    	
    }

    public long getUid() {
        if (!values.containsKey(FIELDS[UID])) {
            return 0L;
        }
        try {
            return Long.parseLong(values.get(FIELDS[UID]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("News: ")
                .append(" {");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
