package com.example.timecontroler.Fragmentos;

import android.os.Bundle;
import android.widget.TextClock;

import com.example.timecontroler.R;


public class Reloj extends Frag  {

private TextClock txtClock;
    @Override
    protected int getLayout() {
        return R.layout.fragment_reloj;
    }

    @Override
    public void start(Boolean is) {

    }

    @Override
    public void diseño(int i) {

    }

    @Override
    public Boolean isStart() {
        return null;
    }

    @Override
    public Boolean isIniciado() {
        return null;
    }

    @Override
    public void setIniciado(boolean booolean) {

    }

    @Override
    public long getBase() {
        return 0;
    }

    @Override
    public void setBasepausa(long l) {

    }

    @Override
    public long getBasepausa() {
        return 0;
    }

    @Override
    public void setbase(long l) {

    }


    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        txtClock = rootView.findViewById(R.id.txtClock);
        txtClock.setMaxLines(1);



    }


}
