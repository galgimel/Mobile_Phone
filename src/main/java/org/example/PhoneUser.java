package org.example;

import lombok.*;

@Getter
@Setter
@ToString(of = {"id","name","mobile_phone"})
@AllArgsConstructor
@RequiredArgsConstructor

public class PhoneUser {
    int id;
    @NonNull
    String name;
    @NonNull
    int mobile_phone;
}