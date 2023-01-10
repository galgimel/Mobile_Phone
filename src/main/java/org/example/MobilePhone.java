package org.example;

import lombok.*;

@Getter
@Setter
@ToString(of = {"id","brand","model","performance","price"})
@AllArgsConstructor
@RequiredArgsConstructor

public class MobilePhone {
    int id;
    @NonNull
    String brand;
    @NonNull
    String model;
    int performance;
    int price;
    public MobilePhone(@NonNull String brand, @NonNull String model, int performance, int price) {
        this.brand = brand;
        this.model = model;
        this.performance = performance;
        this.price = price;
    }

}
