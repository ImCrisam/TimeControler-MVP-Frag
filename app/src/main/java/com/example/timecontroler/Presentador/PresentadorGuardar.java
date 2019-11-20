package com.example.timecontroler.Presentador;

import android.content.Context;

import com.example.timecontroler.Datos.Datos;
import com.example.timecontroler.Modelos.Registro;
import com.example.timecontroler.Navi.InterfaceGuardar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PresentadorGuardar implements InterfaceGuardar.Presentador {

    private Registro registro;
    private final InterfaceGuardar.View view;
    private CharSequence tempo;
    private int mili;
    private Datos datos;

    public PresentadorGuardar(InterfaceGuardar.View view, Context ctx) {
        this.view = view;
        datos = new Datos(ctx);
    }

    @Override
    public void leer() {

    }

    @Override
    public void guardar(int total) {
        tempo = view.getFragmeny(total).getTime();
        mili = view.getFragmeny(total).getMilis();
        registro.setTotal(tempo.toString() + "." + mili);
    }


    @Override
    public void salvar() {
        datos.agregar(registro);

    }

    @Override
    public void agregar(int i) {
        tempo = view.getFragmeny(i).getTime();
        mili = view.getFragmeny(i).getMilis();

        if (!tempo.toString().equals("00:00" )|| mili!=0) {
            registro.addTiempo(tempo.toString() + "." + mili);
        }
    }

    @Override
    public void new_star() {
        registro = new Registro();
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        String fecha = format0N(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "/" + format0N(mes) + "/" + Calendar.getInstance().get(Calendar.YEAR);
        String hora = format0N(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) + ":" + format0N(Calendar.getInstance().get(Calendar.MINUTE)) + ":" + format0N(Calendar.getInstance().get(Calendar.SECOND));
        int m = Calendar.getInstance().get(Calendar.AM_PM);
        registro.setNombre(hora + " | " + fecha);
    }

    private String format0N(int numero) {
        if (numero <= 9) {
            return "0" + numero;
        }
        return numero + "";
    }
}
