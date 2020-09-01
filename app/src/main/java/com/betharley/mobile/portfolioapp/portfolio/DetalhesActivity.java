package com.betharley.mobile.portfolioapp.portfolio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.betharley.mobile.portfolioapp.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import io.paperdb.Paper;

public class DetalhesActivity extends AppCompatActivity {
    private PortfolioItem portfolioItem;
    private TextView title;
    private TextView description;
    private ImageView imagem;

    private static String keyUsuario;
    private int tituloDescription = 0;
    private Dialog categoryDialog;

    private DatabaseReference databaseRef;
    //private static String posicaoImagem = "";

    private TextView item_likes_quantidade, item_quantidade;
    private ImageView item_foto_like, item_foto_comentar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        //getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Paper.init(this);

        item_likes_quantidade = findViewById(R.id.item_likes_quantidade);
        item_foto_like = findViewById(R.id.item_foto_like);
        item_quantidade = findViewById(R.id.item_quantidade);
        item_foto_comentar = findViewById(R.id.item_foto_comentar);


        //Bundle bundle = getIntent().getExtras();
        title = findViewById(R.id.detalhes_title);
        description = findViewById(R.id.detalhes_description);
        imagem = findViewById(R.id.detalhes_imagem);

        //posicaoImagem = getIntent().getStringExtra("posicaoImagem");
        portfolioItem = (PortfolioItem) getIntent().getSerializableExtra("portfolio");


        if(  portfolioItem != null ){
           /* if( posicao < 10 )
                posicaoImagem = "0"+posicao;*/

            //verificarKey();
            //verificarKeyLocal();
            setData();
            //like_Foto();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //quantidade_likes();
    }

    private void setData() {
        title.setText( portfolioItem.getTitle() );
        description.setText( portfolioItem.getDescription() );
        Glide.with( getApplicationContext() ).load( portfolioItem.getUrl() ).into( imagem );
    }

    private void verificarKey(){
        keyUsuario = Paper.book().read( "keyUsuario" );
        //Log.i("Galeria "," keyUsuario "+keyUsuario);
        if( keyUsuario == null ){
            DatabaseReference databaseRefKey = FirebaseDatabase.getInstance().getReference().child("Galeria");
            String KeyGeradaDatabase = databaseRefKey.push().getKey();
            Paper.book().write("keyUsuario", KeyGeradaDatabase);
            //Log.i("Galeria ","KeyGeradaDatabase "+KeyGeradaDatabase);
        }

        if( keyUsuario != null ){
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put(""+keyUsuario, "true");


            FirebaseDatabase.getInstance().getReference()
                    .child("Permissao")
                    .child(keyUsuario).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //Toast.makeText(DetalhesActivity.this, ""+dataSnapshot, Toast.LENGTH_SHORT).show();
                    if( dataSnapshot.exists() ){
                        //Toast.makeText(DetalhesActivity.this, "Permissao ---- "+dataSnapshot.getRef(), Toast.LENGTH_SHORT).show();
                        String key = dataSnapshot.getKey();
                        String permissao = dataSnapshot.getValue().toString();
                        if( permissao.equals("Permissao")){
                            //Toast.makeText(DetalhesActivity.this, "Permissao 2222---- "+dataSnapshot.getRef(), Toast.LENGTH_SHORT).show();

                            ImageView imageTitulo = findViewById(R.id.detalhes_alterar_titulo);
                            ImageView imageDescricao = findViewById(R.id.detalhes_alterar_descricao);
                            imageTitulo.setVisibility( View.VISIBLE );
                            imageDescricao.setVisibility( View.VISIBLE );


                            imageTitulo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //ADICIONAR UM ALERT DIALOG
                                    tituloDescription = 1;
                                    //setCategoryDialog();
                                }
                            });
                            imageDescricao.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //Toast.makeText(DetalhesActivity.this, "Aqui", Toast.LENGTH_SHORT).show();
                                    tituloDescription = 2;
                                    //setCategoryDialog();
                                }
                            });
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //DatabaseReference databaseRefPermissao = FirebaseDatabase.getInstance().getReference().child("Permissao");

        }
    }

    private void setCategoryDialog(){
        final EditText categoryname;
        Button addBtn, add_Cancelar;


        //Toast.makeText(DetalhesActivity.this, "setCategoryDialog", Toast.LENGTH_SHORT).show();

        //DIALOG ADICIONAR
        categoryDialog = new Dialog(this);
        categoryDialog.setContentView(R.layout.add_category_dialog);
       // categoryDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.shape_rounded_box));
        categoryDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        categoryDialog.setCancelable( true );

        categoryname = categoryDialog.findViewById(R.id.add_nome_category);
        addBtn = categoryDialog.findViewById(R.id.add_botao);
        add_Cancelar = categoryDialog.findViewById(R.id.add_Cancelar);

        if( tituloDescription == 1 )
            categoryname.setText( title.getText().toString() );
        else
            categoryname.setText( description.getText().toString() );


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome_category = categoryname.getText().toString().trim();

                if( nome_category == null && nome_category.isEmpty() &&  tituloDescription == 1 ){
                    categoryname.setError("Requirrido");
                    Toast.makeText(DetalhesActivity.this, "Adicione um nome para o Titulo da postagem", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( nome_category == null && nome_category.isEmpty() &&  tituloDescription == 2 ){
                    categoryname.setError("Requirrido");
                    Toast.makeText(DetalhesActivity.this, "Adicione uma descrição para a postagem", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( tituloDescription == 1 )
                    uploadCategoryName(nome_category);
                else
                    uploadCategoryName(nome_category);


                categoryDialog.dismiss();
            }
        });

        add_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tituloDescription = 0;
                categoryDialog.dismiss();
            }
        });

        categoryDialog.show();
    }
    private void uploadCategoryName(final String textoAlterado){
        HashMap<String, Object> mapa = new HashMap<>();
        if( tituloDescription == 1 )
            mapa.put("title", textoAlterado );
        else
            mapa.put("description", textoAlterado );

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("Galeria")
                .child( portfolioItem.getKey() ).updateChildren(mapa)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if( task.isSuccessful() ){
                            Toast.makeText(DetalhesActivity.this, "Alteração realizada com Sucesso", Toast.LENGTH_SHORT).show();
                            setTitleDescription(textoAlterado);
                        }else {
                            Toast.makeText(DetalhesActivity.this, "Erro ao salvar dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setTitleDescription(String textoAlterado){
        if( tituloDescription == 1 )
            title.setText( textoAlterado );
        else
            description.setText( textoAlterado );
    }


    private void verificarKeyLocal(){
        keyUsuario = Paper.book().read( "keyUsuario" );
        //Log.i("Galeria "," keyUsuario "+keyUsuario);
        if( keyUsuario == null ){
            databaseRef = FirebaseDatabase.getInstance().getReference().child("Galeria");
            String KeyGeradaDatabase = databaseRef.push().getKey();
            Paper.book().write("keyUsuario", KeyGeradaDatabase);
            //Log.i("Galeria ","KeyGeradaDatabase "+KeyGeradaDatabase);
        }

        //Log.i("Galeria "," keyUsuario "+keyUsuario);
    }


    private void like_Foto(){
        if( keyUsuario != null ){

            item_foto_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(), "LIKE", Toast.LENGTH_SHORT).show();

                    DatabaseReference likesRef = FirebaseDatabase.getInstance().getReference()
                            .child("Likes").child( portfolioItem.getKey() ).child( keyUsuario );

                    likesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if( dataSnapshot.exists() ){

                            }else{

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });

            //MUDA DE TELA PARA COMENTAR
            item_foto_comentar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "COMENTAR", Toast.LENGTH_SHORT).show();
                    //IR PARA ACTIVITY COMENTÁIO
                    //Intent intentComentario = new Intent(getApplicationContext(), ComentarioActivity.class);
                    //intentComentario.putExtra( "portfolioItem", portfolioItem );
                    //startActivity( intentComentario );
                }
            });
            //MUDA DE TELA PARA COMENTAR
            item_quantidade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "COMENTAR", Toast.LENGTH_SHORT).show();
                    //IR PARA ACTIVITY COMENTÁIO
                    //Intent intentComentario = new Intent(getApplicationContext(), ComentarioActivity.class);
                    //intentComentario.putExtra( "portfolioItem", portfolioItem );
                    //startActivity( intentComentario );
                }
            });

        }else{
            Toast.makeText(getApplicationContext(), "No momento você não tem autorização", Toast.LENGTH_SHORT).show();
        }


       //VERIFICAR SE ESTA COM/SEM LIKES NA IMAGEM
        FirebaseDatabase.getInstance().getReference().child("Likes")
                .child( portfolioItem.getKey() ).child(keyUsuario)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if( dataSnapshot.exists() ){
                            //item_foto_like.setImageResource(R.drawable.icone_like);
                        }else{
                            //item_foto_like.setImageResource(R.drawable.icone_dislike);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        //VERIFICA A QUANTIDADE DE COMENTARIOS
        FirebaseDatabase.getInstance().getReference()
                .child("Comentarios").child( portfolioItem.getKey() )
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if( dataSnapshot.exists() ){
                            String quantidade = String.valueOf( dataSnapshot.getChildrenCount() );
                            item_quantidade.setText( quantidade+ "  Comentários" );
                        }else{
                            item_quantidade.setText(  "0  Comentários" );
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

    }
    private void quantidade_likes(){
        //QUANTIDADE DE LIKES DE CADA IMAGEM
        FirebaseDatabase.getInstance().getReference().child("Likes")
                .child( portfolioItem.getKey() )
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if( dataSnapshot.exists() ){
                            String totalLikes = String.valueOf( dataSnapshot.getChildrenCount() );
                            item_likes_quantidade.setText( totalLikes + "  Likes" );
                        }else{
                            item_likes_quantidade.setText( "0  Likes" );
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
