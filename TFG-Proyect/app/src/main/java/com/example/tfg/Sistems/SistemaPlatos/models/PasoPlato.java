package com.example.tfg.Sistems.SistemaPlatos.models;

import com.example.tfg.R;
import com.example.tfg.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PasoPlato extends RealmObject {

    public static final String PASO_PARTIR = "Partir";
    public static final String PASO_HORNO = "Hornear";
    public static final String PASO_BOL = "Lavar";
    public static final String PASO_ESPECIAS = "Sazonar";
    public static final String PASO_FREIR = "Freir";
    public static final String PASO_COCER = "Cocer";
    public static final String PASO_BATIR = "Batir";
    public static final String PASO_EMPLATAR = "Emplatar";


    @PrimaryKey
    private int id;
    private String paso;
    private int time;
    private int imageCocina;

    public PasoPlato() {}

    public PasoPlato(String paso, int time, int imageCocina) {
        this.id = MyApplication.PasoID.incrementAndGet();
        this.paso = paso;
        this.time = time;
        this.imageCocina = imageCocina;
    }

    //SETTERS
    //------------------
    public void setPaso(String paso) {
        this.paso = paso;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setImageCocina(int imageCocina) {
        this.imageCocina = imageCocina;
    }

    // GETTERS
    //-------------

    public int getId() {
        return id;
    }

    public String getPaso() {
        return paso;
    }

    public int getTime() {
        return time;
    }

    public int getImageCocina() {
        return imageCocina;
    }

    public static int getImageCocinaText(String tipo){
        switch (tipo){
            case PASO_PARTIR:
                return R.drawable.paso_partir;
            case PASO_HORNO:
                return R.drawable.paso_horno;
            case PASO_BOL:
                return R.drawable.paso_bol;
            case PASO_ESPECIAS:
                return R.drawable.paso_especias;
            case PASO_FREIR:
                return R.drawable.pasos_freir;
            case PASO_COCER:
                return R.drawable.paso_cocer;
            case PASO_BATIR:
                return R.drawable.paso_batir;
            case PASO_EMPLATAR:
                return R.drawable.pasos_emplatar;

            default:
                return 0;
        }
    }
}
