package org.example;

public class MobilePhone {
    int id;
    String brand;
    String model;
    int performance;
    int price;

    public MobilePhone(final int id, final String brand, final String model, final int performance, final int price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.performance = performance;
        this.price = price;
    }

    public MobilePhone(final String brand, final String model, final int performance, final int price) {
        this.brand = brand;
        this.model = model;
        this.performance = performance;
        this.price = price;
    }

    public MobilePhone(final String brand, final String model) {
        this.brand = brand;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", performance=" + performance +
            ", price=" + price +
            '}';
    }
}
