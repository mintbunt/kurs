package com.example.myapplication1.help;

public class pills  {
    private String name,id;

    public pills() {
    }

    public pills(String name, String id) {
        this.id = id;
        this.name = name;
    }

    public String getIdPill() {
        return id;
    }

    public String getNamePill() {
        return name;
    }

}
