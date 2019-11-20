package com.example.timecontroler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Chronometer;

public class Chronometer2 extends Chronometer {
    private Runnable runnable;
    private Thread thread;
    private int milis = 0;
    private boolean isInit = false;


    public Chronometer2(Context context) {
        super(context);

    }

    @Override
    public void start() {
        super.start();
        if (!isInit) {
            milisegundos();
        }
        thread.start();
    }

    @Override
    public void stop() {
        super.stop();
        if (thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public int getMiliS() {
        return milis;
    }

    private void milisegundos() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        milis += 100;
                        if (milis == 1000) {
                            milis = 0;
                        }
                        Thread.sleep(100);
                        System.out.println(milis + "");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        thread = new Thread(runnable);
    }


    public Chronometer2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Chronometer2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Chronometer2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setCountDown(boolean countDown) {
        super.setCountDown(countDown);
    }

    @Override
    public boolean isCountDown() {
        return super.isCountDown();
    }

    @Override
    public boolean isTheFinalCountDown() {
        return super.isTheFinalCountDown();
    }

    @Override
    public void setBase(long base) {
        super.setBase(base);
    }

    @Override
    public long getBase() {
        return super.getBase();
    }

    @Override
    public void setFormat(String format) {
        super.setFormat(format);
    }

    @Override
    public String getFormat() {
        return super.getFormat();
    }

    @Override
    public void setOnChronometerTickListener(OnChronometerTickListener listener) {
        super.setOnChronometerTickListener(listener);
    }

    @Override
    public OnChronometerTickListener getOnChronometerTickListener() {
        return super.getOnChronometerTickListener();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    @Override
    protected void onWindowVisibilityChanged(int visibility) {

        super.onWindowVisibilityChanged(visibility);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {

        super.onVisibilityChanged(changedView, visibility);
    }

    @Override
    public CharSequence getContentDescription() {
        return super.getContentDescription();
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }


}
