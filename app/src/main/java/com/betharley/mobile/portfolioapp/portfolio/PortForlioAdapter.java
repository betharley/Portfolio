package com.betharley.mobile.portfolioapp.portfolio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betharley.mobile.portfolioapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class PortForlioAdapter extends RecyclerView.Adapter<PortForlioAdapter.PortfolioViewHolder> {
    private List<PortfolioItem> lista;
    private Context context;

    public PortForlioAdapter(List<PortfolioItem> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public PortfolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_portfolio, parent, false);
        return new PortfolioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioViewHolder holder, final int position) {
        final PortfolioItem portfolioItem = lista.get(position);

        //holder.title.setText( String.valueOf(position+1) );
        holder.title.setText( portfolioItem.getTitle() );
        Glide.with(context).load(portfolioItem.getUrl()).into(holder.imagem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicao = position + 1;
                String posicaoImagem = "";
                if( posicao < 10 )
                    posicaoImagem = "0"+posicao;

                //Toast.makeText(context, ""+posicao, Toast.LENGTH_SHORT).show();
                Intent intentDetalhes = new Intent( context, DetalhesActivity.class);
                intentDetalhes.putExtra("portfolio", portfolioItem);
                intentDetalhes.putExtra( "posicaoImagem", posicaoImagem );

                context.startActivity( intentDetalhes );
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static final class PortfolioViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        ImageView imagem;

        public PortfolioViewHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.item_port_title);
            imagem = itemView.findViewById(R.id.item_port_imagem);
            //description = itemView.findViewById(R.id.item_port_title);
        }
    }
}
