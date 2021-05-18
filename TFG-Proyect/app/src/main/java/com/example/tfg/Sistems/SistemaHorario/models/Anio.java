package com.example.tfg.Sistems.SistemaHorario.models;

import com.example.tfg.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Anio extends RealmObject {

    @PrimaryKey
    private int id;
    private int anio;
    RealmList<Mes> list_mes;

    //CONSTRUCTOR
    //-----------------------
    public Anio() {}

    public Anio(int anio) {
        this.id = MyApplication.AnioID.incrementAndGet();
        this.anio = anio;
    }

    //SETTERS
    //---------------------
    public void setAnio(int anio) {
        this.anio = anio;
    }

    //GETTERS
    //------------------
    public int getId() {
        return id;
    }

    public int getAnio() {
        return anio;
    }

    public RealmList<Mes> getList_mes() {
        return list_mes;
    }
}
