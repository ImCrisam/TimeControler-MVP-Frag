package com.example.timecontroler;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timecontroler.Adapter.AdapterResultados;
import com.example.timecontroler.Datos.Datos;
import com.example.timecontroler.Modelos.Registro;

import java.util.ArrayList;
import java.util.List;

public class Resultados extends FragmentActivity {

    private ImageButton btnBack, btnDelete;
    private RecyclerView recy;
    private String tipo;
    private Datos datos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        datos = new Datos(this);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
        recy = findViewById(R.id.recycle);

        tipo=getIntent().getStringExtra("tipo");

        List<Registro> list = datos.leer();
        System.out.println(list);
        AdapterResultados adapterResultados = new AdapterResultados(this, datos.leer());
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(adapterResultados);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.clear();
                recy.setAdapter(new AdapterResultados(getApplicationContext(), datos.leer()));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private List<Registro> listaDePruebas() {
        List<Registro> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Registro r = new Registro();
            r.setNombre("prueba");
            r.setTotal("999:59:59");
            r.addTiempo("3:00");
            r.addTiempo("3:00");
            r.addTiempo("3:00");
            r.addTiempo("3:00");
            r.addTiempo("3:00");
            r.addTiempo("3:00");
            result.add(r);
        }
        return result;
    }
}
