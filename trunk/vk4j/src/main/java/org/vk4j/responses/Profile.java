package org.vk4j.responses;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 20:22:19
 */
public class Profile {

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
                                            "has_mobile",
                                            "rate",
                                            "contacts",
                                            "education"
    };

    private final Map<String, String> values = new HashMap();

    public Profile() {

    }

    public void put(String field, String value) {
        values.put(field, value);
    }

    public String get(String field) {
        return values.get(field);
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
}
