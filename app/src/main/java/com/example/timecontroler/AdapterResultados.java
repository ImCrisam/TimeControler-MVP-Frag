package com.example.timecontroler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timecontroler.Modelos.Registro;

import java.util.List;

public class AdapterResultados extends  RecyclerView.Adapter<AdapterResultados.ViewHolder> {

    private List<Registro> listR;
    private Context ctx;


    public AdapterResultados(Context context, List<Registro> listR) {
        this.listR = listR;
        this.ctx = context;
    }

    @NonNull
    @Override
    public AdapterResultados.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResultados.ViewHolder holder, int position) {
        holder.nombre.setText(listR.get(position).getNombre());
        holder.total.setText(listR.get(position).getTotal());
        holder.recy.setLayoutManager(new LinearLayoutManager(ctx));
        holder.recy.setAdapter(new AdapterTiempos(ctx, listR.get(position).getTiempos()));
    }

    @Override
    public int getItemCount() {
        return listR.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, total;
        RecyclerView recy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            total = itemView.findViewById(R.id.total);
            recy = itemView.findViewById(R.id.recycle);
        }

    }
}
