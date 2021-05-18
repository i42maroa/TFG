package com.example.tfg.Sistems.SistemaAlmacenamiento.Models;

import com.example.tfg.app.MyApplication;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PlatoAlmacenado extends RealmObject  {

    @PrimaryKey
    private int id;
    private int idPlato;
    private Date fechaAlmacen;
    private int raciones;

    public PlatoAlmacenado() { }

    public PlatoAlmacenado(int idPlato, Date fechaAlmacen) {
        this.id = MyApplication.PlatoAlmacenID.incrementAndGet();
        this.idPlato = idPlato;
        this.fechaAlmacen = fechaAlmacen;
        this.raciones = 1;
    }

    //SETTERS

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public void setFechaAlmacen(Date fechaAlmacen) {
        this.fechaAlmacen = fechaAlmacen;
    }

    public void setRaciones(int raciones) {
        this.raciones = raciones;
    }

    //GETTERS
    //------------------


    public int getId() {
        return id;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public Date getFechaAlmacen() {
        return fechaAlmacen;
    }

    public int getRaciones() {
        return raciones;
    }

    public void aumentarRacion(){
        raciones = raciones + 1;
    }

    public boolean disminuirRacion(){
        if (raciones > 0){
            raciones = raciones - 1;
            return true;
        }
        else
            return false;
    }
}
