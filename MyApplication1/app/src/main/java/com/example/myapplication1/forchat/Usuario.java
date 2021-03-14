package com.example.myapplication1.forchat;

public class Usuario {

    private String id;
    private String name;
    private String surname;
    private String patronymic;

    public Usuario(String id, String name, String surname, String patronymic) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getUsuario() {
        return id;
    }

    public String getImya() {
        return name + " " + patronymic;
    }

    public String getNombre() {
        return name + " " + surname + " " + patronymic;
    }

}
