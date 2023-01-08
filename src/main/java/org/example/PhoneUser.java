package org.example;

public class PhoneUser {
    int id;
    String name;
    int mobile_phone;

    public PhoneUser(final int id, final String name, final int mobile_phone) {
        this.id = id;
        this.name = name;
        this.mobile_phone = mobile_phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
            "id=" + id +
            ", name='" + name + '\'' +
            ", mobile_phone=" + mobile_phone +
            '}';
    }
}