package org.example;

import lombok.*;

@Getter
@Setter
@ToString(of = {"id","name","mobile_phone"})
@AllArgsConstructor

public class PhoneUser {
    int id;
    String name;
    int mobile_phone;

    public PhoneUser(String name, int mobile_phone) {
        this.name = name;
        this.mobile_phone = mobile_phone;
    }
}