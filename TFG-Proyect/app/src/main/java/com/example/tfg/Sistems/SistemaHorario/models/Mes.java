package com.example.tfg.Sistems.SistemaHorario.models;

import com.example.tfg.Sistems.SistemaPlatos.models.VNut;
import com.example.tfg.app.MyApplication;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Mes extends RealmObject {

    public static String MES_ENERO = "Enero";
    public static String MES_FEBRERO = "Febrero";
    public static String MES_MARZO = "Marzo";
    public static String MES_ABRIL = "Abril";
    public static String MES_MAYO = "Mayo";
    public static String MES_JUNIO = "Junio";
    public static String MES_JULIO = "Julio";
    public static String MES_AGOSTO = "Agosto";
    public static String MES_SEPTIEMBRE = "Septiembre";
    public static String MES_OCTUBRE = "Octubre";
    public static String MES_NOVIEMBRE = "Noviembre";
    public static String MES_DICIEMBRE = "Diciembre";

    private int id;
    private int mes;
    private int anio;
    private int calorias;

    RealmList<Dia> listFranja;

    public Mes() {
    }

    public Mes(int anio, int mes) {
        this.id = MyApplication.MesID.incrementAndGet();;
        this.mes = mes;
        this.anio = anio;
        listFranja = new RealmList<Dia>();
    }

    //SETTERS
    //------------------
    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    //GETTERS
    //--------------------------
    public int getId() {
        return id;
    }

    public int getAnio() {
        return anio;
    }

    public int getMes() {
        return mes;
    }

    public int getCalorias() {
        return calorias;
    }

    public RealmList<Dia> getListDias() {
        return listFranja;
    }

    public static String getNombMes(int num_mes){
        switch (num_mes){
            case 1:
                return MES_ENERO;
            case 2:
                return MES_FEBRERO;
            case 3:
                return MES_MARZO;
            case 4:
                return MES_ABRIL;
            case 5:
                return MES_MAYO;
            case 6:
                return MES_JUNIO;
            case 7:
                return MES_JULIO;
            case 8:
                return MES_AGOSTO;
            case 9:
                return MES_SEPTIEMBRE;
            case 10:
                return MES_OCTUBRE;
            case 11:
                return MES_NOVIEMBRE;
            case 12:
                return MES_DICIEMBRE;

            default:
                return "";
        }
    }

    public static int getDiasMes(int mes){
        switch (mes){
            case 1:
                return 31;
            case 2:
                return 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;

            default:
                return 0;
        }
    }

    public ArrayList<VNut> getEstadistica(){
        ArrayList<VNut> arrayVNUTaux = new ArrayList<VNut>();
        VNut vNutAux = new VNut();
        vNutAux.setCero();

        for(int i=1;i<=getListDias().size();i++){
            vNutAux.sumVal(this.getListDias().get(i-1).getvNuts());

            if(i % 7 == 0){
                arrayVNUTaux.add(new VNut(vNutAux.getCalorias(), vNutAux.getProteinas(),vNutAux.getHidratosCarbono(),vNutAux.getFibra(), vNutAux.getAzucares(), vNutAux.getGrasas(), vNutAux.getSal()));
                vNutAux.setCero();
            }
        }

        arrayVNUTaux.add(vNutAux);
        return arrayVNUTaux;
    }

    public int getDiaMasCalorico(){
        int calMax=0, diaAux=-1;

        for(int i =0; i<getListDias().size();i++){
            if(getListDias().get(i).getvNuts().getCalorias() > calMax){
                calMax = getListDias().get(i).getvNuts().getCalorias();
                diaAux=getListDias().get(i).getId();
            }
        }

        return diaAux;
    }

    public ArrayList<Integer> getValEstadisticas(){
        int cal=0, cont = 0, nuevCal;

        for(int i =0; i<getListDias().size();i++){
            nuevCal = getListDias().get(i).getvNuts().getCalorias();
            cal=cal + nuevCal;

            if(nuevCal != 0)
                cont++;
        }

        final int medDia = (cal == 0)? 0 : cal/cont, calT=cal;
        return new ArrayList<Integer>() {{
            add(medDia);
            add(calT);
        }};
    }
}
