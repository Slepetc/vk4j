package org.vk4j.responses;

/**
 * Created by Vladimir Grachev.
 * Date: 27.04.2010
 * Time: 20:22:19
 */
public class Profile {

    private Long uid;
    private String firstName;
    private String lastName;

    public Profile(Long uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Profile: ")
                .append(" {")
                .append("uid=").append(uid).append(", ")
                .append("firstName=").append(firstName).append(", ")
                .append("lastName=").append(lastName).append("}");
        return sb.toString();
    }

    //    private String nickName;
//    private String sex;
//    private String birthday;
//    private String city;
//    private String country;
//    private String timezone;
//    private String photo;
//    private String photo_medium;
//    private String photo_big;
//    private String has_mobile;
//    private String rate;
//    private String home_phone;
//    private String mobile_phone;
//    private String university;
//    private String university_name;
//    private String faculty;
//    private String faculty_name;
//    private String graduation;
}
