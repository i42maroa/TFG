package com.example.tfg.Sistems.SistemaPlatos.models;

import android.graphics.Color;

import com.example.tfg.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import lecho.lib.hellocharts.util.ChartUtils;

public class VNut extends RealmObject {

    public static final String GRASAS = "Grasa";
    public static final String FIBRA = "Fibra";
    public static final String HIDRATOS = "Hidratos Carbono";
    public static final String AZUCARES = "Azúcares";
    public static final String PROTEINAS = "Proteína";
    public static final String SAL = "Sal";

    private int calorias;
    private double grasas, fibra, hidratosCarbono, azucares, proteinas, sal;

    //CONSTRUCTOR
    //-------------------
    public VNut() {}

    public VNut(int calorias, double proteinas, double carbohidratos, double fibra, double azucares, double grasas, double sal) {
        this.calorias = calorias; this.grasas = grasas; this.fibra = fibra; this.hidratosCarbono = carbohidratos;
        this.azucares = azucares; this.proteinas = proteinas; this.sal = sal;
    }

    public static int getColor(String valor){
        switch (valor){
            case PROTEINAS:
                return ChartUtils.COLOR_BLUE;
            case HIDRATOS:
                return ChartUtils.COLOR_GREEN;
            case FIBRA:
                return ChartUtils.COLOR_GREEN;
            case AZUCARES:
                return ChartUtils.COLOR_GREEN;
            case GRASAS:
                return ChartUtils.COLOR_ORANGE;
            case SAL:
                return R.color.colSal;

            default:
                return Color.BLACK;
        }
    }

    public static String getNameNut(int pos){
        switch (pos){
            case 0:
                return PROTEINAS;
            case 1:
                return HIDRATOS;
            case 2:
                return FIBRA;
            case 3:
                return AZUCARES;
            case 4:
                return GRASAS;
            case 5:
                return SAL;
            default:
                return "";
        }
    }

    public double getValNut(int pos){
        switch (pos){
            case 0:
                return getProteinas();
            case 1:
                return getHidratosCarbono();
            case 2:
                return getFibra();
            case 3:
                return getAzucares();
            case 4:
                return getGrasas();
            case 5:
                return getSal();
            default:
                return 0;
        }
    }

    //SETTERS
    //-----------------------------

    public int getCalorias() {
        return calorias;
    }

    public double getGrasas() {
        return grasas;
    }

    public double getFibra() {
        return fibra;
    }

    public double getHidratosCarbono() {
        return hidratosCarbono;
    }

    public double getAzucares() {
        return azucares;
    }

    public double getProteinas() {
        return proteinas;
    }

    public double getSal() {
        return sal;
    }

    public List<Double> getValores(){
        return new ArrayList<Double>(){{
            add(getProteinas());
            add(getHidratosCarbono());
            add(getFibra());
            add(getAzucares());
            add(getGrasas());
            add(getSal());
        }};
    }


    public void sumVal(VNut vNut, int cantidad){
        this.calorias = this.calorias + (vNut.getCalorias() * cantidad)/100;
        this.grasas = this.grasas + (vNut.getGrasas() * cantidad)/100;
        this.fibra = this.fibra + (vNut.getFibra() * cantidad)/100;
        this.hidratosCarbono = this.hidratosCarbono + (vNut.getHidratosCarbono() * cantidad)/100;
        this.azucares = this.azucares + (vNut.getAzucares() * cantidad)/100;
        this.proteinas = this.proteinas + (vNut.proteinas * cantidad)/100;
        this.sal = this.sal + (vNut.getSal() * cantidad)/100;
    }

    public void sumVal(VNut vNut){
        this.calorias = this.calorias + vNut.getCalorias();
        this.grasas = this.grasas + vNut.getGrasas() ;
        this.fibra = this.fibra + vNut.getFibra();
        this.hidratosCarbono = this.hidratosCarbono + vNut.getHidratosCarbono();
        this.azucares = this.azucares + vNut.getAzucares();
        this.proteinas = this.proteinas + vNut.proteinas;
        this.sal = this.sal + vNut.getSal();
    }

    public void setCero(){
        this.calorias = 0;
        this.grasas = 0;
        this.fibra = 0;
        this.hidratosCarbono = 0;
        this.azucares = 0;
        this.proteinas = 0;
        this.sal = 0;
    }
}
