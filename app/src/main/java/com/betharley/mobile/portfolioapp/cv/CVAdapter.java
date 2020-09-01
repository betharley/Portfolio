package com.betharley.mobile.portfolioapp.cv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betharley.mobile.portfolioapp.R;

import java.util.List;

public class CVAdapter extends RecyclerView.Adapter<CVAdapter.CVViewHolder> {
    private List<CVItem> lista;

    public CVAdapter(List<CVItem> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public CVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cv, parent, false);
        return new CVViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull CVViewHolder holder, int position) {
        CVItem cvItem = lista.get( position );

        holder.titulo.setText( cvItem.getTitle() );
        holder.description.setText( cvItem.getDescription() );
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static final class CVViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView description;

        public CVViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.cv_titulo);
            description = itemView.findViewById(R.id.cv_description);
        }
    }

}
