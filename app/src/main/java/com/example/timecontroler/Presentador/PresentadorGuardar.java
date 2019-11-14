package com.example.timecontroler.Presentador;

import com.example.timecontroler.Navi.InterfaceGuardar;

public class PresentadorGuardar implements InterfaceGuardar.Presentador {


    private final InterfaceGuardar.View view;

    public PresentadorGuardar(InterfaceGuardar.View view) {
        this.view = view;
    }

    @Override
    public void leer() {

    }

    @Override
    public void guardar() {

    }

    @Override
    public void obtener(int i) {
        CharSequence x = view.getFragmeny(i).getTime();
        System.out.println(x);
    }
}
