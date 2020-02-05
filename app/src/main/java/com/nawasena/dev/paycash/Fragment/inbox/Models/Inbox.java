package com.nawasena.dev.paycash.Fragment.inbox.Models;

public class Inbox {
    private String history_name;
    private String desc;
    private String publishedAt;
    private String sender;

    public Inbox(String history_name, String desc, String publishedAt, String sender) {
        this.history_name = history_name;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.sender = sender;
    }

    public String getHistory_name() {
        return history_name;
    }

    public void setHistory_name(String history_name) {
        this.history_name = history_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
