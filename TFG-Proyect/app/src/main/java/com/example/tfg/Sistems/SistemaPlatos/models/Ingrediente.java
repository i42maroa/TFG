package com.example.tfg.Sistems.SistemaPlatos.models;

import com.example.tfg.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


public class Ingrediente extends RealmObject {



    @PrimaryKey
    private int id;
    @Required
    private String nombre;
    @Required
    private String categoria;

    private VNut vNuts;

    private int image;

    private int pesoUnidad;



    //CONSTRUCTOR
    //----------------------
    public Ingrediente() {
    }

    public Ingrediente(String nombre, String categoria, VNut vNut, int image, int pesoUnidad) {
        this.id = MyApplication.IngredienteID.incrementAndGet();
        this.nombre = nombre;
        this.categoria = categoria;
        this.image=image;
        this.vNuts = vNut;
        this.pesoUnidad = pesoUnidad;
    }

    //SETTERS
    //--------------------------
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setvNuts(VNut vNuts) {
        this.vNuts = vNuts;
    }

    public void setImage(int image) {
        this.image = image;
    }

    //GETTERS
    //--------------------------

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public VNut getvNuts() {
        return vNuts;
    }

    public int getCal() { return vNuts.getCalorias();}

    public int getImage() {
        return image;
    }

    public int getPesoUnidad() {
        return pesoUnidad;
    }
}
