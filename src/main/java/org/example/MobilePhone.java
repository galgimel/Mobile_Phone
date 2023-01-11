package org.example;

import lombok.*;

@Getter
@Setter
@ToString(of = {"id","brand","model","performance","price"})
@AllArgsConstructor

public class MobilePhone {
    int id;
    String brand;
    String model;
    int performance;
    int price;
    public MobilePhone(String brand, String model, int performance, int price) {
        this.brand = brand;
        this.model = model;
        this.performance = performance;
        this.price = price;
    }
    public MobilePhone(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
}
