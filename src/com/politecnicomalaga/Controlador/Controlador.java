package com.politecnicomalaga.Controlador;


import com.politecnicomalaga.Persistencia.ControlTrabajos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Controlador {
    //ESTADO //////////////////////////////////////////////////////////////////////////////////

    // Lista de los tipos de vehiculos para el taller
    protected String[] tiposVehiculo = {"Coche", "Moto", "Furgón", "Camión"};

    private static final String ficheroPorCobrar = "porCobrar.csv";
    private static final String ficheroCobrado = "cobrado.csv";

    // Lista que guarda los trabajos realizados
    //protected ArrayList<TrabajoTaller> trabajosRealizados;

    //Lista de Trabajos a cobrar
    //protected ArrayList<TrabajoTaller> trabajosACobrar;

    //Variable String para erroes
    protected String mensajeError;

    //El truco
    public static Controlador singleton;

    //Instanciamos las clases ControlTrabajos
    ControlTrabajos miControlTrabajosPorCobrar;
    ControlTrabajos miControlTrabajosCobrados;

    //CONTRUCTOR //////////////////////////////////////////////////////////////////////////////////

    public Controlador(){
        //this.trabajosACobrar = new ArrayList<TrabajoTaller>();
        //this.trabajosRealizados = new ArrayList<TrabajoTaller>();
        miControlTrabajosPorCobrar = new ControlTrabajos();
        miControlTrabajosCobrados = new ControlTrabajos();
        cargarTrabajosPorCobrar();
        mensajeError = "";
    }

    //METODOS //////////////////////////////////////////////////////////////////////////////////

    private void cargarTrabajosPorCobrar() {
        String sCSV = "";
        FileReader fr;
        Scanner sc;
        byte lines = 0;

        try {
            fr = new FileReader(ficheroPorCobrar);
            sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                sCSV = sc.nextLine();
                miControlTrabajosPorCobrar.addTrabajo(sCSV);
                lines++;
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //El trucamen
    public static Controlador getSingleton(){
        if(singleton == null){
            singleton = new Controlador();
        }
        return singleton;
    }

    public void altaTrabajo(String sCSV){
        miControlTrabajosPorCobrar.addTrabajo(sCSV);

        grabarTrabajoTaller(sCSV,ficheroPorCobrar);
    }

    private void grabarTrabajoTaller(String sCSV, String sFichero){
        //abrimos una escritura al fichero
        FileWriter fw = null;
        try {
            fw = new FileWriter(sFichero,true);
            //adicionamos al final la línea tal cual y un "\n"
            fw.write(sCSV + "\n");
            fw.flush();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fw != null){
                try{
                    fw.close();
                } catch (IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void actualizarTrabajoTaller(String sCSV, String sFichero){
        //abrimos una escritura al fichero
        FileWriter fw = null;
        try {
            fw = new FileWriter(sFichero,true);
            //adicionamos al final la línea tal cual y un "\n"
            fw.write(sCSV + "\n");
            fw.flush();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fw != null){
                try{
                    fw.close();
                } catch (IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void cobrarTrabajo(String sCSV, String horasReales){
        miControlTrabajosCobrados.addTrabajo(miControlTrabajosPorCobrar.cobrar(sCSV,horasReales));
    }

    public String getFicheroCobrado(){
        return ficheroCobrado;
    }

    //Getter de la lista
    public String[] getTrabajosACobrar() {
        return miControlTrabajosPorCobrar.getListaTrabajosVista();
    }

    // llamamos al arrayList para coger el contador
    public String[] getTrabajosRealizados() {
        return miControlTrabajosCobrados.getListaTrabajosVista();
    }

    public String[] getTiposVehiculo(){
        return tiposVehiculo;
    }
}
