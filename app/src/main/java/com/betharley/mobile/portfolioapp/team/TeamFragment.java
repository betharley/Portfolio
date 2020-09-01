package com.betharley.mobile.portfolioapp.team;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.betharley.mobile.portfolioapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment {
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<TeamItem> items;

    private ImageView team_android_galeria;

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void setItems(){
       /* items.add( new TeamItem( "Cupcake", getString(R.string.Cupcake), R.drawable.cupcake_android_1_5) );
        items.add( new TeamItem( "Donut", getString(R.string.Donut), R.drawable.donut_android_1_6) );
        items.add( new TeamItem( "Eclair", getString(R.string.Eclair), R.drawable.eclair_android_2_1) );

        items.add( new TeamItem( "Froyo", getString(R.string.Froyo), R.drawable.froyo_android_2_2) );
        items.add( new TeamItem( "Gingerbread", getString(R.string.Gingerbread), R.drawable.gingerbreak_android_2_3) );
        items.add( new TeamItem( "Honeycomb", getString(R.string.Honeycomb), R.drawable.honeycomb_android_3_0) );

        items.add( new TeamItem( "Ice Cream Sandwich", getString(R.string.Ice_Cream_Sandwich), R.drawable.ice_cream_sandwich_android_4_0) );
        items.add( new TeamItem( "Jelly Bean", getString(R.string.Jelly_Bean), R.drawable.jelly_bean_android_4_1) );
        items.add( new TeamItem( "KitKat", getString(R.string.Kitkat), R.drawable.kitkat_android_4_4) );

        items.add( new TeamItem( "Lollipop", getString(R.string.Lollipop), R.drawable.lollipop_android_5_0) );
        items.add( new TeamItem( "Marshmallow", getString(R.string.Marshmallow), R.drawable.marshmallow_android_5_0) );
        items.add( new TeamItem( "Nougat", getString(R.string.Nougat), R.drawable.nougat_android_6_0) );

        items.add( new TeamItem( "Oreo", getString(R.string.Oreo), R.drawable.oreo_android_8_0) );
        items.add( new TeamItem( "Pie", getString(R.string.Pie), R.drawable.pie_android_9) );*/
        //items.add( new TeamItem( "Android 10", getString(R.string.lorem_text3), R.drawable.userspace) );
        //items.add( new TeamItem( "Android 11", getString(R.string.lorem_text3), R.drawable.userspace) );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        items = new ArrayList<>();
        setItems();

        //recyclerView = view.findViewById(R.id.recyclerview_team);
        //team_android_galeria = view.findViewById(R.id.team_android_galeria);

/*        teamAdapter = new TeamAdapter( items );
        layoutManager = new LinearLayoutManager( getActivity() );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( teamAdapter );*/

        /*team_android_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentAndroid = new Intent(getContext(), AndroidActivity.class);
                //getActivity().startActivity( intentAndroid );
            }
        });*/
        return view;
    }

    public void abrir_galeria_android(View view){

    }
}
