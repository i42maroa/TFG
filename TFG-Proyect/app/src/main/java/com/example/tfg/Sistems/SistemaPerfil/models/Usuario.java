package com.example.tfg.Sistems.SistemaPerfil.models;

import com.example.tfg.app.Const;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Usuario extends RealmObject  {

    @PrimaryKey
    private int id;
    private String nombre, correo;
    private String fecha_nacimiento;
    private double altura, peso;
    private int genero;

    private int calDes;
    private int calAl;
    private int calCena;
    private int calComp;

    public static int USUARIO_HOMBRE = 0;
    public static int USUARIO_MUJER = 1;

    public Usuario() {}

    public Usuario(String nombre, String correo, int genero, String fecha_nacimiento, double altura, double peso) {
        this.id = 1;
        this.nombre = nombre;
        this.correo = correo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.altura = altura;
        this.genero = genero;
        this.peso = peso;


        this.calDes = Const.CAL_DESAYUNO;
        this.calAl = Const.CAL_ALMUERZO;
        this.calCena = Const.CAL_CENA;
        this.calComp = Const.CAL_COMP;
    }

    //SETTERS
    //--------------------
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    //CALORIAS FRANJAS
    public void setCalDes(int calDes) {
        this.calDes = calDes;
    }

    public void setCalAl(int calAl) {
        this.calAl = calAl;
    }

    public void setCalCena(int calCena) {
        this.calCena = calCena;
    }

    public void setCalComp(int calComp) {
        this.calComp = calComp;
    }


    //GETTERS
    //----------------------------

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public int getGenero() {
        return genero;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    //CALORIAS FRANJAS

    public int getCalDes() {
        return calDes;
    }

    public int getCalAl() {
        return calAl;
    }

    public int getCalCena() {
        return calCena;
    }

    public int getCalComp() {
        return calComp;
    }
}
