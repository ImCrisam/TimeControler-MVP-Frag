package com.example.timecontroler;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timecontroler.Modelos.Registro;

import java.util.ArrayList;
import java.util.List;

public class Resultados extends FragmentActivity {

    private ImageButton btnBack, btnDelete;
    private RecyclerView recy;
    private String tipo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
        recy = findViewById(R.id.recycle);

        tipo=getIntent().getStringExtra("tipo");


        AdapterResultados adapterResultados = new AdapterResultados(this, listaDePruebas());
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(adapterResultados);

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
