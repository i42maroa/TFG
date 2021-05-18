package com.example.tfg.Sistems.SistemaHorario.models;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;
import com.example.tfg.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FranjaHorario extends RealmObject {

    public static String FRANJA_DESAYUNO = "Desayuno";
    public static String FRANJA_COMP = "Complementos";
    public static String FRANJA_ALMUERZO = "Almuerzo";
    public static String FRANJA_CENA = "Cena";

    @PrimaryKey
    private int id;
    private int dia;
    private int mes;
    private int anio;
    private int franja;

    private String nombFranja;

    private int calFranja;
    private VNut vNuts;

    RealmList<Plato> platos;

    //CONSTRUCTOR

    public FranjaHorario(){}

    public FranjaHorario(int anio, int mes, int dia, int franja) {
        this.id = MyApplication.FranjaID.incrementAndGet();
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.franja = franja;
        this.calFranja = 0;
        this.vNuts = new VNut();
        vNuts.setCero();
        this.platos = new RealmList<Plato>();
    }

    //SETTERS

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setFranja(int franja) {
        this.franja = franja;
    }

    //GETTERS

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

    public int getFranja() {
        return franja;
    }

    public int getCalFranja() { return calFranja;}

    public static String getNombFranja(int franja) {
        switch (franja){
            case 0:
                return FRANJA_DESAYUNO;
            case 1:
                return FRANJA_ALMUERZO;
            case 2:
                return FRANJA_CENA;
            case 3:
                return FRANJA_COMP;
            default:
                return "";
        }
    }

    public VNut getvNuts() {
        return vNuts;
    }

    public RealmList<Plato> getPlatos() {
        return platos;
    }

    public void refrescarVN(){
        calFranja=0;
        vNuts.setCero();
        for(int i = 0; i<platos.size(); i++ ) {
            calFranja = calFranja + platos.get(i).getCaloriasTotales();
            vNuts.sumVal(platos.get(i).getvNuts());
        }
    }

    public static int getColorFranja(int numCal, int calPersona){
        if (numCal == 0 )
            return R.color.franjaVacia;

        if (numCal < calPersona - calPersona*2/10)
            return R.color.colorFranjaVerde;
        else if (numCal > calPersona + calPersona*2/10)
            return R.color.colorFranjaRojo;
        else
            return R.color.colorFranjaNaranja;
    }
}
