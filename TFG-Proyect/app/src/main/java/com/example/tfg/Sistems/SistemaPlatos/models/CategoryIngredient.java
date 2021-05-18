package com.example.tfg.Sistems.SistemaPlatos.models;

import com.example.tfg.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CategoryIngredient  extends RealmObject  {
    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private int image;

    RealmList<Plato> listPlatos;

    public CategoryIngredient() {
    }

    public CategoryIngredient(String nombreCategoria, String description, int image) {
        this.id =  MyApplication.CategoriaIDIngrediente.incrementAndGet();
        this.name = nombreCategoria;
        this.description = description;
        this.image = image;
    }

    //SETTERS
    //-------------
    public void setNombreCategoria(String nombreCategoria) {
        this.name = nombreCategoria;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    //GETTERS
    //----------------
    public int getId() {
        return id;
    }

    public String getNombreCategoria() {
        return name;
    }

    public RealmList<Plato> getListPlatos() {
        return listPlatos;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
