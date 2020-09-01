package com.betharley.mobile.portfolioapp.cv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betharley.mobile.portfolioapp.R;
import com.betharley.mobile.portfolioapp.cv.CVAdapter;
import com.betharley.mobile.portfolioapp.cv.CVItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CVFragment extends Fragment {
    private RecyclerView recyclerView;
    private CVAdapter cvAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<CVItem> items;

    public CVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<>();
        setItems();

        recyclerView = view.findViewById(R.id.recyclerview_cv);
        layoutManager = new LinearLayoutManager( getActivity() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);

        cvAdapter = new CVAdapter(items);
        recyclerView.setAdapter(cvAdapter);

    }

    private void setItems(){
        items.clear();

        items.add( new CVItem( "Portfólio", getString(R.string.cv1)));
        items.add( new CVItem( "Algumas Práticas", getString(R.string.cv2)));
        items.add( new CVItem( "O Resultado do que Aprendi", getString(R.string.cv3)));
        items.add( new CVItem( "Iniciante, Intermediário, Avançado", getString(R.string.cv4)));
        items.add( new CVItem( "Modelo de Apresentação", getString(R.string.cv5)));
        items.add( new CVItem( "Sempre Divulgue", getString(R.string.cv6)));
        items.add( new CVItem( "Emprego na Área", getString(R.string.cv7)));
        items.add( new CVItem( "O que Você Quer se Tornar ?", getString(R.string.cv8)));
        items.add( new CVItem( "Dicas de Estudos", getString(R.string.cv9)));

    }
}
