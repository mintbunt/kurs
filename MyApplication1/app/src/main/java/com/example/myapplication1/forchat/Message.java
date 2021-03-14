package com.example.myapplication1.forchat;

public class Message {

    private String idMessage;
    private String message;
    private String userOrig;
    private String userDest;
    private String name;
    private String patronymic;

    public Message(String idMessage, String message, String userOrig, String userDest, String name, String patronymic) {
        this.idMessage = idMessage;
        this.message = message;
        this.userOrig = userOrig;
        this.name = name;
        this.patronymic = patronymic;
        this.userDest = userDest;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserOrig() {
        return userOrig;
    }

    public String getName() { return name + " " + patronymic; }

    public String getUserDest() { return userDest; }

}
