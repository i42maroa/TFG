package com.example.tfg.Sistems.SistemaPlatos.models;

import com.example.tfg.app.MyApplication;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Plato extends RealmObject {

    public static int FAVORITO_SI = 1;
    public static int FAVORITO_NO = 0;

    public static String DIFICULTAD_FACIL = "Fácil";
    public static String DIFICULTAD_MEDIA = "Media";
    public static String DIFICULTAD_DIFICIL ="Difícil";

    public static int PLATO_PRED = 0;
    public static int PLATO_USU = 1;

    @PrimaryKey
    private int id;
    @Required
    private String nombre;
    @Required
    private String dificultad;

    private int tiempoElav;
    @Required
    private String descripcion;

    private int image;
    @Required
    private Date createdAt;

    private int calTotal;
    RealmList<PasoPlato> pasosPlato;

    private int favorito;
    private int modCreacion;

    RealmList<IngredienteCantidad> ingredientes;

    private VNut vNuts;

    //Constructor
    public Plato(String nombrePlato, String dificultad, int tiempElav, String descripcion, int image, int modCreacion) {
        this.id = MyApplication.PlatoID.incrementAndGet();
        this.nombre = nombrePlato;
        this.dificultad = dificultad;
        this.tiempoElav = tiempElav;
        this.descripcion = descripcion;
        this.image = image;
        this.createdAt = new Date();
        this.calTotal = 0;
        this.ingredientes = new RealmList<IngredienteCantidad>();
        this.pasosPlato = new RealmList<PasoPlato>();
        this.favorito = FAVORITO_NO;
        this.modCreacion = modCreacion;
        this.vNuts = new VNut(0, 0, 0, 0, 0, 0, 0);
    }

    public Plato(){}

    //SETTERS
    //--------------------
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public void setTiempoElav(int tiempoElav) {
        this.tiempoElav = tiempoElav;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public void setModCreacion(int modCreacion) {
        this.modCreacion = modCreacion;
    }

    //GETTERS
    //-------------------

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public int getTiempoElav() {
        return tiempoElav;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImage() {
        return image;
    }

    public int getCaloriasTotales(){ return vNuts.getCalorias(); }

    public Date getCreatedAt() {
        return createdAt;
    }

    public RealmList<IngredienteCantidad> getIngredientes() {
        return ingredientes;
    }

    public RealmList<PasoPlato> getPasosPlato() {
        return this.pasosPlato;
    }

    public int getFavorito() {
        return favorito;
    }

    public VNut getvNuts() {
        return vNuts;
    }

    public int getModCreacion() {
        return modCreacion;
    }

    public void refrescarVN(){
        this.vNuts.setCero();

        for(int i=0;i<ingredientes.size();i++)
            this.vNuts.sumVal(ingredientes.get(i).getIngrediente().getvNuts(), ingredientes.get(i).getGramos());
    }
}
