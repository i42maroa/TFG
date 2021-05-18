package com.example.tfg.Sistems.SistemaPlatos.models;

import com.example.tfg.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class IngredienteLista extends RealmObject {

    @PrimaryKey
    private int id;
    private int cantidad;
    private int gramos;
    Ingrediente ingrediente;

    public IngredienteLista() {}

    public IngredienteLista(Ingrediente ingrediente, int cantidad, int gramos ) {
        this.id = MyApplication.IngredienteListaID.incrementAndGet();
        this.cantidad = cantidad;
        this.gramos = gramos;
        this.ingrediente = ingrediente;
    }

    //SETTERS
    //---------------------
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setGramos(int gramos) {
        this.gramos = gramos;
    }

    //GETTERS
    //---------------
    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getGramos() {
        return gramos;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void sumarIngrediente(IngredienteCantidad iC){
        gramos = gramos + iC.getGramos();
        cantidad = gramos / getIngrediente().getPesoUnidad() + 1;
    }
}
