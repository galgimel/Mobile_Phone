package org.example;

public class Store {
    int id;
    String name;

    public Store(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Store(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Store{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}