package org.vk4j.open.responses;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Message {

    public static final int BODY = 0;
    public static final int TITLE = 1;
    public static final int DATE = 2;
    public static final int UID = 3;    
    public static final int MID = 4;
    public static final int READ_STATE = 5;

    public static final String[] FIELDS = { "body",
    										"title",
                                            "date",
    										"uid",
                                            "mid",
                                            "read_state"
    };

    private final Map<String, String> values = new HashMap();
    private Date date = null;

    public Message() {

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
        if (date != null || !values.containsKey(FIELDS[DATE])) {
            return date;
        }
        
        date = new Date();
        //Calendar calendar = Calendar.getInstance();
        //calendar.set

        return date;
    }
    
    public long getId() {
        if (!values.containsKey(FIELDS[MID])) {
            return 0L;
        }
        try {
            return Long.parseLong(values.get(FIELDS[MID]));
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
            return Long.parseLong(values.get(FIELDS[UID]));
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
    		return Integer.parseInt(values.get(FIELDS[READ_STATE]));
    	}
    	catch (NumberFormatException e) {
    		return null;    		
    	}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message: ")
                .append(" {");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
