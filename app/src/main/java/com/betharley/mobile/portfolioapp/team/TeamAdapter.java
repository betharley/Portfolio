package com.betharley.mobile.portfolioapp.team;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betharley.mobile.portfolioapp.R;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private List<TeamItem> lista;

    public TeamAdapter(List<TeamItem> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        TeamItem teamItem = lista.get( position );
        holder.setData( teamItem.getName(), teamItem.getDescription(), teamItem.getImagem() );
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static final class TeamViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView description;
        ImageView image;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.team_item_name);
            description = itemView.findViewById(R.id.team_item_description);
            image = itemView.findViewById(R.id.team_item_imagem);
        }

        public void setData(String name, String description, int imagem){
            this.name.setText( name );
            this.description.setText( description );
            //this.image.setImageResource( imagem );
        }
    }
}
