package org.example;

public class MobilePhoneToStore {
    int store_id;
    int mobile_phone_id;

    public MobilePhoneToStore(int store_id, int mobile_phone_id) {
        this.store_id = store_id;
        this.mobile_phone_id = mobile_phone_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getMobile_phone_id() {
        return mobile_phone_id;
    }

    public void setMobile_phone_id(int mobile_phone_id) {
        this.mobile_phone_id = mobile_phone_id;
    }

    @Override
    public String toString() {
        return "MobilePhoneToStore{" +
            "store_id=" + store_id +
            ", mobile_phone_id=" + mobile_phone_id +
            '}';
    }
}
