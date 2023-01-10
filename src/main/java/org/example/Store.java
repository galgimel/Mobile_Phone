package org.example;

import lombok.*;

@Getter
@Setter
@ToString(of = {"id","name"})
@AllArgsConstructor
@RequiredArgsConstructor

public class Store {
    int id;
    @NonNull
    String name;
}