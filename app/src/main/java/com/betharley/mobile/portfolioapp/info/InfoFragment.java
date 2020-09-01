package com.betharley.mobile.portfolioapp.info;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.betharley.mobile.portfolioapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    //atributo da classe.

    private TextView info_key;
    private static String keyUsuario;

    private AlertDialog alerta;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        //AQUI VAI AS INFORMAÇÕES
        info_key = view.findViewById(R.id.info_key);

        Paper.init(getContext());

        info_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Teste de uma chave", Toast.LENGTH_SHORT).show();
                verificarKey();
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void verificarKey(){
        keyUsuario = Paper.book().read( "keyUsuario" );
        if( keyUsuario == null ){
            DatabaseReference databaseRefKey = FirebaseDatabase.getInstance().getReference().child("Galeria");
            String KeyGeradaDatabase = databaseRefKey.push().getKey();
            Paper.book().write("keyUsuario", KeyGeradaDatabase);
        }
        if( keyUsuario != null ){
            //Paper.book().read("keyUsuario");
            String keyCortada = keyUsuario.substring( keyUsuario.length()-8, keyUsuario.length());

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity() );
            //define o titulo
            builder.setTitle("Key do App");
            //define a mensagem
            builder.setMessage("A chave gerada da instalação do Aplicativo é: " +  keyCortada);
            //define um botão como positivo
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    //Toast.makeText(getActivity(), "positivo=" + arg1, Toast.LENGTH_SHORT).show();
                }
            });

            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();

        }
    }

}
