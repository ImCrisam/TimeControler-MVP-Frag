package com.example.timecontroler;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.timecontroler.Fragmentos.Frag;
import com.example.timecontroler.Navi.InterfaceGuardar;
import com.example.timecontroler.Presentador.PresentadorGuardar;
import com.example.timecontroler.Presentador.PresentadorReloj;

public class ActivityReloj extends FragmentActivity implements com.example.timecontroler.Navi.ActivityReloj.View, InterfaceGuardar.View {

    private PresentadorReloj presenter;
    private ImageButton btn1, btn2, btn3, btnResult, btnRelojA;
    private FrameLayout frameLy1, frameLy2;
    private boolean isplay = true;
    private boolean isbegin = true;
    private int intFragReset = 2;
    private int intFragTotal = 1;
    private int intBtnGrande = 1;
    private Intent intent;
    private PresentadorGuardar guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        guardar = new PresentadorGuardar(this, getApplicationContext());
        presenter = new PresentadorReloj(this);

        btn1 = findViewById(R.id.btnPlay);
        btn2 = findViewById(R.id.btnReplay);
        btn3 = findViewById(R.id.btnStop);

        btnResult = findViewById(R.id.btnResultados);
        btnRelojA = findViewById(R.id.btnTiempo);
        btnRelojA.setEnabled(false);

        frameLy1 = findViewById(R.id.frameLy1);
        frameLy2 = findViewById(R.id.frameLy2);

        presenter.getPrimerFragment();

        //----- botones superiores-----//
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityReloj.this, Resultados.class);
                startActivity(intent);

            }
        });


//----- botones play------///
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

        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                intBtnGrande = 1;
                return true;
            }
        });
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                intBtnGrande = 2;
                return true;
            }
        });
        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                intBtnGrande = 3;
                return true;
            }
        });

//----------- acciones fragmentos----//
        View.OnLongClickListener onLongClick = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isbegin) {
                    acciones(3);
                }
                presenter.cambio();
                if (intFragReset == 1) {
                    intFragTotal = 1;
                    intFragReset = 2;
                } else {
                    intFragTotal = 2;
                    intFragReset = 1;
                }
                return true;
            }
        };
        frameLy1.setOnLongClickListener(onLongClick);
        frameLy2.setOnLongClickListener(onLongClick);


        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acciones(intBtnGrande);
            }
        };
        frameLy1.setOnClickListener(onClick);
        frameLy2.setOnClickListener(onClick);

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


    private void acciones(int i) {
        if (isbegin) {
            guardar.new_star();
        }
        switch (i) {
            case 1:
                isbegin = false;
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
                guardar.agregar(intFragReset);
                presenter.replay();
                break;
            case 3:
                guardar.agregar(intFragReset);
                guardar.guardar(intFragTotal);

                presenter.reset(1);
                presenter.reset(2);
                presenter.start_Stop(1);
                presenter.start_Stop(2);
                presenter.stop();
                btn1.setImageDrawable(getDrawable(R.drawable.play));
                isplay = false;
                isbegin = true;
                break;
        }
    }
}
