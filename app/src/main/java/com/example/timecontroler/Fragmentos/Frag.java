package com.example.timecontroler.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.timecontroler.Navi.FragmentNavigation;

public abstract class Frag extends Fragment implements FragmentNavigation.View {

    protected View rootView;
    protected FragmentNavigation.Presenter navigationPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        rootView = inflater.inflate(getLayout(), container, false);
        return rootView;
    }

    @Override
    public void atachPresenter(FragmentNavigation.Presenter presenter) {
        navigationPresenter = presenter;
    }

    protected abstract int getLayout();

    public abstract void start(Boolean is);

    public abstract void diseño(int i);

    public abstract Boolean isStart();

    public abstract Boolean isIniciado();

    public abstract void setIniciado(boolean booolean);

    public abstract long getBase();

    public abstract void setBasepausa(long l);

    public abstract long getBasepausa();

    public abstract void setbase(long l);

    public abstract CharSequence getTime();

    public abstract int getMilis();


    public abstract void setMilis(int milis);


}
