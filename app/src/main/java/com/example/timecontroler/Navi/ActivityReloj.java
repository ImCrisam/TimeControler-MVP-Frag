package com.example.timecontroler.Navi;

import com.example.timecontroler.Fragmentos.Frag;

public interface ActivityReloj {

    interface View {
        void setFragment1(Frag fragment1);

        void setFragment2(Frag fragment2);

        Frag getFragmeny(int i);

    }

    interface Presentador {
        void getPrimerFragment();
        void stop();
        void start_Stop(int i);
        void cambio();
        void reset(int i);
         void replay();
    }
}
