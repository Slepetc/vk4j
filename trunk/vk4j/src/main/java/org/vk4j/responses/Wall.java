package org.vk4j.responses;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Wall {

    public static final int FROM_ID = 0;
    public static final int TO_ID = 1;
    public static final int DATE = 2;
    public static final int TEXT = 3;    
    public static final int ID = 4;
    public static final int ONLINE = 5;
    public static final int MEDIA = 6;

    public static final String[] FIELDS = { "from_id",
                                            "to_id",
                                            "date",
                                            "text",
                                            "id",
                                            "online",
                                            "media"
    };

    private final Map<String, Object> values = new HashMap<String, Object>();

    public Wall() {

    }

    public void put(String field, Object value) {
        values.put(field, value);
    }

    public String get(String field) {
        return (String)values.get(field);
    }

    public String get(int field) {
        return (String)values.get(FIELDS[field]);
    }
    
    public Date getDate() {
        if (!values.containsKey(FIELDS[DATE])) {
            return null;
        }
        // vkontakte returns date stored in the similar format as java.sql.date
        // the only difference is that returned date has no milliseconds and therefore
        // must be multiplied by 1000
        try {
            return new Date(Long.parseLong((String)values.get(FIELDS[DATE])) * 1000);
        }
        catch (NumberFormatException e) {
            return null;
        }    	
    }

    public long getId() {
        if (!values.containsKey(FIELDS[ID])) {
            return 0L;
        }
        try {
            return Long.parseLong((String)values.get(FIELDS[ID]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }

    public long getFromId() {
        if (!values.containsKey(FIELDS[FROM_ID])) {
            return 0L;
        }
        try {
            return Long.parseLong((String)values.get(FIELDS[FROM_ID]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }
    
    public long getToId() {
        if (!values.containsKey(FIELDS[TO_ID])) {
            return 0L;
        }
        try {
            return Long.parseLong((String)values.get(FIELDS[TO_ID]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }
    
    public Boolean getOnline() {
    	if (!values.containsKey(FIELDS[ONLINE])) {
    		return null;
    	}
		return (values.get(FIELDS[ONLINE]) == "1");
    }
    
    public Media getMedia() {
    	if (!values.containsKey(FIELDS[MEDIA])) {
    		return null;
    	}
		return (Media)values.get(FIELDS[MEDIA]);    	
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wall: ")
                .append(" {");

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
