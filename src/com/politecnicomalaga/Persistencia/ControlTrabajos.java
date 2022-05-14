package com.politecnicomalaga.Persistencia;

import com.politecnicomalaga.Modelo.TrabajoTaller;

import java.util.LinkedList;
import java.util.List;

public class ControlTrabajos {

    //ESTADO
    List<TrabajoTaller> listaTrabajos;

    //COMPORTAMIENTO
    //Constructor
    public ControlTrabajos(){
        listaTrabajos = new LinkedList<TrabajoTaller>();
    }

    //Otros métodos
    public void addTrabajo(String sCSV){
        TrabajoTaller tt;
        tt = new TrabajoTaller(sCSV);
        listaTrabajos.add(tt);
    }

    public void addTrabajo(TrabajoTaller tt){
        listaTrabajos.add(tt);
    }

    public TrabajoTaller cobrar(String sCSV, String sHorasReales){
        TrabajoTaller tt;

        tt = new TrabajoTaller(sCSV);

        //Buscamos el tt en la lista
        for (int i = 0; i < listaTrabajos.size(); i++) {
            if (tt.compareTo(listaTrabajos.get(i)) == 0){
                tt.sethTrabRealizadas(Float.valueOf(sHorasReales));
                listaTrabajos.remove(i);
                return tt;
            }
        }
        return tt;
    }

    public String[] getListaTrabajosVista(){
        String[] resultado = new String[listaTrabajos.size()];

        for (int i = 0; i < listaTrabajos.size(); i++) {
            resultado[i] = listaTrabajos.get(i).toString();
        }
        return resultado;
    }
}