package com.example.tfg.Sistems.SistemaHorario.models;

import com.example.tfg.app.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Semana  extends RealmObject {

    private int id;
    RealmList<Dia> dias;
    private int diaInicio, mesInicio;
    private int diaFin, mesFin;

    public Semana (){    }

    public Semana(int diaInicio, int mesInicio, int diaFin, int mesFin){
        this.id = 1;
        this.diaInicio = diaInicio;
        this.mesInicio = mesInicio;
        this.diaFin = diaFin;
        this.mesFin = mesFin;
        this.dias = new RealmList<Dia>();
    }

    public int getDiaInicio() {
        return diaInicio;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public int getDiaFin() {
        return diaFin;
    }

    public int getMesFin() {
        return mesFin;
    }

    public void cargarDias() {
        Dia dia = getRealm().where(Dia.class).equalTo("dia", diaInicio).equalTo("mes", mesInicio).findFirst();
        Dia dia2 = getRealm().where(Dia.class).equalTo("dia", diaFin).equalTo("mes", mesFin).findFirst();
        int id = dia.getId();
        int id2 = dia2.getId();

        for(int i=id;i<id2+1;i++){
            getDias().add(getRealm().where(Dia.class).equalTo("id", i).findFirst());
        }
    }

    public void setDias(int diaInicio, int mesInicio, int diaFin, int mesFin){
        this.diaInicio = diaInicio;
        this.mesInicio = mesInicio;
        this.diaFin = diaFin;
        this.mesFin = mesFin;
        actualizarDias();
    }

    public void actualizarDias(){
        getDias().removeAll(dias);
        cargarDias();
    }

    public RealmList<Dia> getDias() {
        return dias;
    }
}
