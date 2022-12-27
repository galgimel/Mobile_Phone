package org.example;

public class PhoneUser {
    int user_id;
    String name;
    int mobile_phone;

    public PhoneUser(int user_id, String name, int mobile_phone) {
        this.user_id = user_id;
        this.name = name;
        this.mobile_phone = mobile_phone;
    }

    public PhoneUser(String name, int mobile_phone) {
        this.name = name;
        this.mobile_phone = mobile_phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(int mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    @Override
    public String toString() {
        return "PhoneUser{" +
            "user_id=" + user_id +
            ", name='" + name + '\'' +
            ", mobile_phone=" + mobile_phone +
            '}';
    }
}
