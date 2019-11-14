package com.example.timecontroler.Navi;

import com.example.timecontroler.Fragmentos.Frag;

public interface InterfaceGuardar {


    interface View {

        Frag getFragmeny(int i);

    }

    interface Presentador {
        void leer();

        void guardar();

        void obtener(int i);
    }

}
