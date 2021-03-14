package com.example.myapplication1.help;

public class pacients {
    private String name,surname,patronymic,id;

    public pacients() {
    }

    public pacients(String id, String name, String surname, String patronymic) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name + " " + surname + " " + patronymic;
    }

    public void setName(String name, String surname, String patronymic) {
        this.name = name + " " + surname + " " + patronymic;
    }
}
