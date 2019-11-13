package com.example.timecontroler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterTiempos extends RecyclerView.Adapter<AdapterTiempos.ViewHolder> {
    private Context ctx;
    private List<String> tiempos;

    public AdapterTiempos(Context ctx, List<String> tiempos) {
        this.ctx = ctx;
        this.tiempos = tiempos;
    }

    @NonNull
    @Override
    public AdapterTiempos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item_historial, parent, false);
        return new AdapterTiempos.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTiempos.ViewHolder holder, int position) {
        holder.number.setText(position+1+"");
        holder.timer.setText(tiempos.get(position));
    }

    @Override
    public int getItemCount() {
        return tiempos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView number, timer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.numbre);
            timer = itemView.findViewById(R.id.timer);
        }
    }
}
