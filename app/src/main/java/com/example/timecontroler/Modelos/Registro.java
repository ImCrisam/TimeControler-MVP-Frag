package com.example.timecontroler.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Registro {

    private String total, nombre;
    private List<String> tiempos;

    public Registro() {
        tiempos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<String> getTiempos() {
        return tiempos;
    }

    public void setTiempos(List<String> tiempos) {
        this.tiempos = tiempos;
    }

    public void addTiempo(String tiempo) {
        tiempos.add(tiempo);
    }

    public int getSizeTiempos() {
        return tiempos.size();
    }

    public String getItem(int i) {
        return tiempos.get(i);
    }


}
