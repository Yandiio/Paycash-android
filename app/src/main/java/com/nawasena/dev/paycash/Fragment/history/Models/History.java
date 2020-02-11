package com.nawasena.dev.paycash.Fragment.history.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

    public History(String status_code, String status_message, String transaction_id, String order_id, String payment_type, String bank, String gross_amount) {
        this.status_code = status_code;
        this.status_message = status_message;
        this.transaction_id = transaction_id;
        this.order_id = order_id;
        this.payment_type = payment_type;
        this.bank = bank;
        this.gross_amount = gross_amount;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getGross_amount() {
        return gross_amount;
    }

    public void setGross_amount(String gross_amount) {
        this.gross_amount = gross_amount;
    }

    @SerializedName("status_code")
    @Expose
    private String status_code;

    @SerializedName("status_message")
    @Expose
    private String status_message;

    @SerializedName("transaction_id")
    @Expose
    private String transaction_id;

    @SerializedName("order_id")
    @Expose
    private String order_id;

    @SerializedName("payment_type")
    @Expose
    private String payment_type;

    @SerializedName("bank")
    @Expose
    private String bank;

    @SerializedName("gross_amount")
    @Expose
    private String gross_amount;


}
