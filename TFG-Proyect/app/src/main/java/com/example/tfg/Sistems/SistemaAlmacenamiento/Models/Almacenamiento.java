package com.example.tfg.Sistems.SistemaAlmacenamiento.Models;

import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Almacenamiento extends RealmObject {

    public static String TIPO_FRIGORIFICO = "Frigorifico";

    @PrimaryKey
    private int id;
    private String nombreAlmacen;
    private String tipo;

    RealmList<PlatoAlmacenado> platos;

    //CONSTRUCTOR
    public Almacenamiento() {
    }

    public Almacenamiento(String nombreAlmacen, String tipo) {
        this.id = MyApplication.AlmacenID.incrementAndGet();
        this.nombreAlmacen = nombreAlmacen;
        this.tipo = tipo;
        this.platos = new RealmList<PlatoAlmacenado>();
    }

    //SETTERS

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    //GETTERS

    public int getId() {
        return id;
    }
    public String getNombreAlmacen() {
        return nombreAlmacen;
    }
    public RealmList<PlatoAlmacenado> getPlatos() {
        return platos;
    }
}
