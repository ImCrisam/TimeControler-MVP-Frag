package com.example.timecontroler.Presentador;

import android.os.SystemClock;
import com.example.timecontroler.Fragmentos.Cronometro;
import com.example.timecontroler.Fragmentos.Frag;
import com.example.timecontroler.Navi.ActivityReloj;
import com.example.timecontroler.Navi.FragmentNavigation;

import java.time.Clock;

public class PresentadorReloj implements FragmentNavigation.Presenter, ActivityReloj.Presentador {

    private ActivityReloj.View view;
    private Frag frag, frag1;
    private Frag fragArry[] = new Frag[2];
    private boolean is[] = new boolean[2];
    private long base[] = new long[2];
    private boolean posicion;


    public PresentadorReloj(ActivityReloj.View view) {
        this.view = view;
    }


    @Override
    public void getPrimerFragment() {
        view.setFragment1(new Cronometro(1));
        view.setFragment2(new Cronometro(2));
        posicion = true;
    }

    @Override
    public void stop() {
        frag = (Frag) view.getFragmeny(1);
        frag.setBasepausa(SystemClock.elapsedRealtime());

        frag.start(false);
        frag = (Frag) view.getFragmeny(2);
        frag.setBasepausa(SystemClock.elapsedRealtime());

        frag.start(false);
    }

    @Override
    public void start_Stop(int i) {
        frag = (Frag) view.getFragmeny(i);
        boolean isIniciando = frag.isIniciado();
        if (!isIniciando) {
            frag.setbase(SystemClock.elapsedRealtime());

            frag.start(true);

        } else {
            boolean start = frag.isStart();
            if (start) {
                frag.setBasepausa(SystemClock.elapsedRealtime());

                frag.start(!start);
            } else {
                frag.setbase(tiempoRealPausa(frag.getBase(), frag.getBasepausa()));

                frag.start(!start);
            }
        }
    }

    @Override
    public void reset(int i) {
        frag = (Frag) view.getFragmeny(i);
        frag.setIniciado(false);
    }

    @Override
    public void replay() {

        if (posicion) {
            frag = (Frag) view.getFragmeny(2);
            frag1 = (Frag) view.getFragmeny(1);
        }else {
            frag = (Frag) view.getFragmeny(1);
            frag1 = (Frag) view.getFragmeny(2);
        }

        System.out.println(SystemClock.elapsedRealtime());
        System.out.println(getMSecon(SystemClock.elapsedRealtime()));
        System.out.println(SystemClock.elapsedRealtime()-getMSecon(frag.getBase()));
        System.out.println(SystemClock.elapsedRealtime()-getMSecon(SystemClock.elapsedRealtime()));
        System.out.println(SystemClock.elapsedRealtime()-getMSecon(SystemClock.elapsedRealtime()-getMSecon(frag.getBase())));
        frag.setbase(SystemClock.elapsedRealtime()-getMSecon(SystemClock.elapsedRealtime()-getMSecon(frag.getBase())));




     /*   reset(i);
        frag = (Frag) view.getFragmeny(k);
        if(frag.isStart()){
            start_Stop(i);
        }else{
            frag = (Frag) view.getFragmeny(i);
            frag.setBasepausa(SystemClock.elapsedRealtime());
            frag.setbase(SystemClock.elapsedRealtime());
        }*/
    }

    private long getMSecon(long base) {
        String tempo="000"+(base) +"";
        tempo= tempo.substring(tempo.length()-3);
        return Long.parseLong(tempo);
    }

    private long tiempoRealPausa(long base, long basepausa) {
        return base + (SystemClock.elapsedRealtime() - basepausa);
    }

    @Override
    public void cambio() {
        fragArry[0] = (Frag) view.getFragmeny(1);
        fragArry[1] = (Frag) view.getFragmeny(2);
        is[0] = fragArry[0].isStart();
        is[1] = fragArry[1].isStart();
        if (is[0] && is[1]) {
            base[0] = fragArry[0].getBase();
            base[1] = fragArry[1].getBase();
            fragArry[0].setbase(base[1]);
            fragArry[1].setbase(base[0]);

        }else{
            base[0] = tiempoRealPausa(fragArry[0].getBase(), fragArry[0].getBasepausa());
            base[1] = tiempoRealPausa(fragArry[1] .getBase(), fragArry[1].getBasepausa());
            fragArry[0].setbase(base[1]);
            fragArry[1].setbase(base[0]);
            fragArry[0].setBasepausa(SystemClock.elapsedRealtime());
            fragArry[1].setBasepausa(SystemClock.elapsedRealtime());
        }

        fragArry[1].start(is[0]);
        fragArry[0].start(is[1]);
        posicion = !posicion;
    }
}
