package org.vk4j.open.responses;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

    public static final int BODY = 0;
    public static final int TITLE = 1;
    public static final int DATE = 2;
    public static final int UID = 3;    
    public static final int MID = 4;
    public static final int READ_STATE = 5;
    public static final int ATTACHMENTS = 6;

    public static final String[] FIELDS = { "body",
    										"title",
                                            "date",
    										"uid",
                                            "mid",
                                            "read_state",
                                            "attachments"
    };

    private final Map<String, Object> values = new HashMap<String, Object>();

    public Message() {

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
        if (!values.containsKey(FIELDS[MID])) {
            return 0L;
        }
        try {
            return Long.parseLong((String)values.get(FIELDS[MID]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }

    public long getUid() {
        if (!values.containsKey(FIELDS[UID])) {
            return 0L;
        }
        try {
            return Long.parseLong((String)values.get(FIELDS[UID]));
        }
        catch (NumberFormatException e) {
            return 0L;
        }
    }
    
    public Integer getReadState() {
    	if (!values.containsKey(FIELDS[READ_STATE])) {
    		return null;
    	}
    	try {
    		return Integer.parseInt((String)values.get(FIELDS[READ_STATE]));
    	}
    	catch (NumberFormatException e) {
    		return null;    		
    	}
    }
    
    public List<String> getAttachments() {
		return (List<String>)values.get(FIELDS[ATTACHMENTS]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message: ")
                .append(" {");

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
