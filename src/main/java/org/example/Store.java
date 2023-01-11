package org.example;

import lombok.*;

@Getter
@Setter
@ToString(of = {"id","name"})
@AllArgsConstructor

public class Store {
    int id;
    String name;

    public Store(String name) {
        this.name = name;
    }
}