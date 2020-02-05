package com.nawasena.dev.paycash.Activities.Checkout.Models;

public class Checkout {

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getGetPublisherAt() {
        return getPublisherAt;
    }

    public void setGetPublisherAt(String getPublisherAt) {
        this.getPublisherAt = getPublisherAt;
    }

    String Harga;
    String Detail;
    String getPublisherAt;
}
