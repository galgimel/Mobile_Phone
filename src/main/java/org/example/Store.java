package org.example;

public class Store {
    int id;
    String store_name;
    int mobile_hone;

    public Store(int id, String store_name, int mobile_hone) {
        this.id = id;
        this.store_name = store_name;
        this.mobile_hone = mobile_hone;
    }

    public Store(String store_name, int mobile_hone) {
        this.store_name = store_name;
        this.mobile_hone = mobile_hone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getMobile_hone() {
        return mobile_hone;
    }

    public void setMobile_hone(int mobile_hone) {
        this.mobile_hone = mobile_hone;
    }

    @Override
    public String toString() {
        return "Store{" +
            "id=" + id +
            ", store_name='" + store_name + '\'' +
            ", mobile_hone=" + mobile_hone +
            '}';
    }
}