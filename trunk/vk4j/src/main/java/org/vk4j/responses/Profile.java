package org.vk4j.responses;

import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 20:22:19
 */
public class Profile {

    public static final int UID = 0;
    public static final int FIRST_NAME = 1;
    public static final int LAST_NAME = 2;
    public static final int NICKNAME = 3;
    public static final int SEX = 4;
    public static final int BDATE = 5;
    public static final int CITY = 6;
    public static final int COUNTRY = 7;
    public static final int TIMEZONE = 8;
    public static final int PHOTO = 9;
    public static final int PHOTO_MEDIUM = 10;
    public static final int PHOTO_BIG = 11;
    public static final int PHOTO_REC = 12;
    public static final int HAS_MOBILE = 13;
    public static final int RATE = 14;
    public static final int HOME_PHONE = 15;
    public static final int MOBILE_PHONE = 16;
    public static final int UNIVERSITY = 17;
    public static final int UNIVERSITY_NAME = 18;
    public static final int FACULTY = 19;
    public static final int FACULTY_NAME = 20;
    public static final int GRADUATION = 21;

    public static final String[] FIELDS = { "uid",
                                            "first_name",
                                            "last_name",
                                            "nickname",
                                            "sex",
                                            "bdate",
                                            "city",
                                            "country",
                                            "timezone",
                                            "photo",
                                            "photo_medium",
                                            "photo_big",
                                            "photo_rec",
                                            "has_mobile",
                                            "rate",
                                            "home_phone",
                                            "mobile_phone",
                                            "university",
                                            "university_name",
                                            "faculty",
                                            "faculty_name",
                                            "graduation"
    };

    private final Map<String, String> values = new HashMap();
    private Date birthday = null;

    public Profile() {

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

    public Date getBirthday() {
        if (birthday != null || !values.containsKey(FIELDS[BDATE])) {
            return birthday;
        }

        String[] bdateStr = values.get(FIELDS[BDATE]).split("\\.");
        if (/*bdateStr.length != 2 || */bdateStr.length != 3) {
            return null;
        }

        int[] bd = new int[3];
        bd[0] = bd[1] = bd[2] =0;
        for (int i = 0; i < bdateStr.length; i++) {
            try {
                bd[i] = Integer.parseInt(bdateStr[i]);
            }
            catch (NumberFormatException e) {
                bd[i] = 0;
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(bd[2], bd[1] - 1, bd[0], 0, 0, 0);
        birthday = calendar.getTime();

        return birthday;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Profile: ")
                .append(" {");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        sb.append("}");

        return sb.toString();
    }

    public long getId() {
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

}
