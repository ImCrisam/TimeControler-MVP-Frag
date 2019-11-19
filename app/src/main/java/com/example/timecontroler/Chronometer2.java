package com.example.timecontroler;

import android.content.Context;
import android.widget.Chronometer;

public class Chronometer2 extends Chronometer {
    private Runnable runnable;
    private Thread thread;
    private int milis=0;
    public Chronometer2(Context context) {
        super(context);
        milisegundos();
        thread = new Thread(runnable);
    }

    @Override
    public void start() {
        super.start();
        thread.start();
    }

    @Override
    public void stop() {
        super.stop();
        thread.stop();
    }

    public int getMiliS() {
        return milis;
    }

    private void milisegundos() {
        runnable = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    milis+=1;
                    if (milis == 1000) {
                        milis=0;
                    }
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
