package com.example.timecontroler;

import androidx.fragment.app.FragmentActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.timecontroler.Fragmentos.Frag;
import com.example.timecontroler.Presentador.PresentadorReloj;

public class ActivityReloj extends FragmentActivity implements com.example.timecontroler.Navi.ActivityReloj.View {

    private PresentadorReloj presenter;
    private ImageButton btn1, btn2, btn3, btnBig;
    private FrameLayout frameLy1, frameLy2;
    private boolean isplay = true;
    private int intBtnGrande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PresentadorReloj(this);
        btn1 = findViewById(R.id.btnPlay);
        btn2 = findViewById(R.id.btnReplay);
        btn3 = findViewById(R.id.btnStop);
        btnBig = findViewById(R.id.btnGrande);
        frameLy1 = findViewById(R.id.frameLy1);
        frameLy2 = findViewById(R.id.frameLy2);
        presenter.getPrimerFragment();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acciones(1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acciones(3);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acciones(2);
            }
        });
        btnBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intBtnGrande==1){
                    if (isplay) {
                        btnBig.setImageDrawable(getDrawable(R.drawable.pause));
                    } else {
                        btnBig.setImageDrawable(getDrawable(R.drawable.play));
                    }
                }
                acciones(intBtnGrande);
            }
        });
        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClikLongBtn(btn1, 1);
                return true;
            }
        });
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClikLongBtn(btn2, 2);
                return true;
            }
        });
        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClikLongBtn(btn3, 3);
                return true;
            }
        });
        btnBig.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                btnBig.setVisibility(View.GONE);
                btnVisible();
                return true;
            }
        });
        frameLy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.cambio();

            }
        });
        frameLy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.cambio();
            }
        });
    }

    @Override
    public void setFragment1(Frag fragment1) {
        fragment1.atachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLy1, fragment1)
                .commit();
    }
    @Override
    public void setFragment2(Frag fragment2) {
        fragment2.atachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLy2, fragment2)
                .commit();
    }

    @Override
    public Frag getFragmeny(int i) {
        switch (i) {
            case 1:
                return (Frag) getSupportFragmentManager().findFragmentById(R.id.frameLy1);
            case 2:
                return (Frag) getSupportFragmentManager().findFragmentById(R.id.frameLy2);
            default:
                return null;
        }
    }
    private void onClikLongBtn(ImageButton boton, int i) {
        btnVisible();
        boton.setVisibility(View.GONE);
        intBtnGrande = i;
        boton.getDrawable();
        btnBig.setVisibility(View.VISIBLE);
        btnBig.setImageDrawable(boton.getDrawable());
    }
    private void btnVisible() {
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
    }
    private void acciones(int i) {
        switch (i) {
            case 1:
                if (isplay) {
                    btn1.setImageDrawable(getDrawable(R.drawable.pause));
                } else {
                    btn1.setImageDrawable(getDrawable(R.drawable.play));
                }
                presenter.start_Stop(2);
                presenter.start_Stop(1);
                isplay = !isplay;
                break;
            case 2:
                presenter.replay();
                break;
            case 3:
                presenter.reset(1);
                presenter.reset(2);
                presenter.start_Stop(1);
                presenter.start_Stop(2);
                presenter.stop();
                btn1.setImageDrawable(getDrawable(R.drawable.play));
                isplay = false;
                break;
        }
    }
}
