package com.example.tfg.Sistems.SistemaHorario.models;

import android.widget.Toast;

import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;
import com.example.tfg.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Dia extends RealmObject {

    private int id;
    private int dia;
    private int mes;
    private int anio;
    private String nombreDia;
    private int caloria;
    RealmList<FranjaHorario> franjas;
    private VNut vNuts;
    private int calTotal;

    public static String DIA_LUNES ="Lunes";
    public static String DIA_MARTES ="Martes";
    public static String DIA_MIERCOLES ="Miércoles";
    public static String DIA_JUEVES ="Jueves";
    public static String DIA_VIERNES ="Viernes";
    public static String DIA_SABADO ="Sábado";
    public static String DIA_DOMINGO ="Domingo";


    //CONSTRUCTOR
    //--------------
    public Dia() {}

    public Dia(int anio, int mes, int dia, String nombreDia) {
        this.id = MyApplication.DiaID.incrementAndGet();
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.calTotal = 0;
        this.vNuts = new VNut();
        this.nombreDia=nombreDia;
        vNuts.setCero();
        franjas = new RealmList<FranjaHorario>();
    }

    //SETTERS
    //-------------------
    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

//GETTERS
    //----------

    public int getAnio() {
        return anio;
    }

    public int getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public VNut getvNuts() {
        return vNuts;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public int getCalTotal() {
        return calTotal;
    }

    public RealmList<FranjaHorario> getFranjas() {
        return franjas;
    }


    public void refrescarVN(){
        vNuts.setCero();
        for(int i=0;i<franjas.size();i++){
            vNuts.sumVal(franjas.get(i).getvNuts());
        }
    }

    public static String getDia(int i){
        switch (i){
            case 0:
                return DIA_LUNES;
            case 1:
                return DIA_MARTES;
            case 2:
                return DIA_MIERCOLES;
            case 3:
                return DIA_JUEVES;
            case 4:
                return DIA_VIERNES;
            case 5:
                return DIA_SABADO;
            case 6:
                return DIA_DOMINGO;
            default:
                return "";
        }
    }
}
