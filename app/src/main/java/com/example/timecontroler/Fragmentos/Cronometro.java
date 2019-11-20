package com.example.timecontroler.Fragmentos;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Chronometer;

import com.example.timecontroler.Chronometer2;
import com.example.timecontroler.R;


public class Cronometro extends Frag {


    private Chronometer2 chronometer;
    private boolean iniciado;
    private boolean star;
    private long basepausa;
    private int textSize = 0;
    private int gravity = 0;


    @Override
    protected int getLayout() {
        return R.layout.fragment_cronometro;
    }

    public Cronometro(int i) {
        star = false;
        setDiseño(i);
    }

    private void setDiseño(int i) {
        switch (i) {
            case 1:
                textSize = 40;
                gravity = Gravity.RIGHT;
                break;
            case 2:
                textSize = 80;
                gravity = Gravity.CENTER;
                break;
        }
    }

    private void refrescar() {
        chronometer.setTextSize(textSize);
        chronometer.setGravity(gravity);
    }

    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        chronometer = rootView.findViewById(R.id.chronometer);
        refrescar();
        chronometer.stop();
        star = false;
        iniciado=false;

    }

    @Override
    public void start(Boolean is) {
        this.star = is;
        if (!iniciado) {
            iniciado = true;
        }
        if (star) {
            chronometer.start();

        } else {
            chronometer.stop();
        }
    }


    @Override
    public void diseño(int i) {
        setDiseño(i);
        refrescar();
    }

    @Override
    public Boolean isStart() {
        return star;
    }

    @Override
    public Boolean isIniciado() {
        return iniciado;
    }

    @Override
    public void setIniciado(boolean booolean) {
        this.iniciado = booolean;
    }


    @Override
    public long getBase() {
        return chronometer.getBase();
    }

    @Override
    public void setBasepausa(long l) {
        basepausa=l;
    }

    @Override
    public long getBasepausa() {
        return basepausa;
    }

    @Override
    public void setbase(long l) {
        chronometer.setBase(l);
    }

    @Override
    public CharSequence getTime() {
        return chronometer.getText();
    }

}