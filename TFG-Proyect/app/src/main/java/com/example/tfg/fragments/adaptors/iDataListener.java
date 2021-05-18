package com.example.tfg.fragments.adaptors;

import java.util.ArrayList;

public interface iDataListener {
    public void sendDayMonth(int dia, int mes);
    public void sendIdPlates(int idPlato, int opc);
    public void enviarIdPlatos(ArrayList<Integer> idPlatos);
    public void enviarIdIngrediente(int idIngrediente);
    public void enviarIdAlmacen(int idAlmacen);
    public void enviarDiaMesAnio(int dia, int mes, int anio);
    public void enviarIdCategoria(int idCategoria);
}
