package com.nawasena.dev.paycash.Fragment.history.Models;

public class History {
    long id;
    String title;
    String desc;


    public History(long id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
