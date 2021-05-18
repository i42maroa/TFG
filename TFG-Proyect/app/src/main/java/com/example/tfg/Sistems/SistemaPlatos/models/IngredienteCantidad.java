package com.example.tfg.Sistems.SistemaPlatos.models;

import com.example.tfg.app.MyApplication;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Clase encargada de indicar cuanta cantidad de algún ingrediente presenta alguna receta
 */

public class IngredienteCantidad extends RealmObject {

    public static String TIPO_PIEZA = "Pieza";
    public static String TIPO_CUCHARADA = "Cuch";
    public static String TIPO_PESO_GRAMOS = "Gramos";
    public static String TIPO_VASO = "Vaso";
    public static String TIPO_MITAD = "Mitad";

    public static final String[] TIPOS_CANTIDADES = new String[]{
        TIPO_PIEZA, TIPO_CUCHARADA, TIPO_PESO_GRAMOS, TIPO_VASO,TIPO_MITAD
    };

    @PrimaryKey
    private int id;
    private int cantidad;
    private String tipo;
    private int gramos;
    Ingrediente ingrediente;


    //CONSTRUCTORS
    //---------------------
    public IngredienteCantidad(){
    }

    public IngredienteCantidad(Ingrediente ing, int cantidad, String tipo, int gramos) {
        this.id = MyApplication.CantidadIngredienteID.incrementAndGet();
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.gramos = gramos;
        this.ingrediente = ing;
    }

    //SETTERS
    //----------------------
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setGramos(int gramos) {
        this.gramos = gramos;
    }
//GETTERS
    //------------------------

     public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public int getGramos() {
        return gramos;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

}
