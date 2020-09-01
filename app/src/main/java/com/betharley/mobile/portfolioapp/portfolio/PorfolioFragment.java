package com.betharley.mobile.portfolioapp.portfolio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.betharley.mobile.portfolioapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PorfolioFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<PortfolioItem> lista;
    private PortForlioAdapter portForlioAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference databaseRef;

    public PorfolioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_porfolio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_portfolio);
        lista = new ArrayList<>();
        //setLista();



        portForlioAdapter = new PortForlioAdapter(lista, getActivity());
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.setAdapter( portForlioAdapter );
    }

    private void setLista(){
        lista.clear();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("Galeria");

        //DatabaseReference dataFotos = databaseRef.child("Galeria");

        //Toast.makeText(getActivity(), databaseRef+"", Toast.LENGTH_SHORT).show();

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists() ){
                    lista.clear();
                    for ( DataSnapshot dado : dataSnapshot.getChildren() ){
                        PortfolioItem portfolioItem = (PortfolioItem) dado.getValue(PortfolioItem.class);
                        portfolioItem.setKey( dado.getKey().toString() );
                        lista.add( portfolioItem );
                    }
                    //dialog.dismiss();
                    //Collections.reverse( lista );
                    portForlioAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //dialog.dismiss();
            }
        });

        /*lista.add( new PortfolioItem(R.drawable.user, "Title1", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.userspace, "Title2", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.uservoyager, "Title3", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.userilust, "Title4", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.usertwo, "Title5", getString(R.string.lorem_text3)));

        lista.add( new PortfolioItem(R.drawable.user, "Title1", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.userspace, "Title2", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.uservoyager, "Title3", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.userilust, "Title4", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.usertwo, "Title5", getString(R.string.lorem_text3)));

        lista.add( new PortfolioItem(R.drawable.user, "Title1", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.userspace, "Title2", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.uservoyager, "Title3", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.userilust, "Title4", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.usertwo, "Title5", getString(R.string.lorem_text3)));

        lista.add( new PortfolioItem(R.drawable.user, "Title1", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.userspace, "Title2", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.uservoyager, "Title3", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.userilust, "Title4", getString(R.string.lorem_text3)));
        lista.add( new PortfolioItem(R.drawable.usertwo, "Title5", getString(R.string.lorem_text3)));
*/

    }
}
