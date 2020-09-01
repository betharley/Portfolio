package com.betharley.mobile.portfolioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;


import com.betharley.mobile.portfolioapp.cv.CVFragment;
import com.betharley.mobile.portfolioapp.home.HomeFragment;
import com.betharley.mobile.portfolioapp.info.InfoFragment;
import com.betharley.mobile.portfolioapp.portfolio.PorfolioFragment;
import com.betharley.mobile.portfolioapp.team.TeamFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private TeamFragment teamFragment;
    private PorfolioFragment porfolioFragment;
    private CVFragment cvFragment;
    private InfoFragment infoFragment;

    private static String keyUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Paper.init(this);
        //verificarKey();

        teamFragment = new TeamFragment();
        porfolioFragment = new PorfolioFragment();
        cvFragment = new CVFragment();
        homeFragment = new HomeFragment();
        infoFragment = new InfoFragment();



        frameLayout = findViewById(R.id.container_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if( item.getItemId() == R.id.menu_home){
                    setFragment(homeFragment);
                    return true;
                }else if( item.getItemId() == R.id.menu_curriculo){
                    setFragment(cvFragment);
                    return true;
                }else if( item.getItemId() == R.id.menu_post){
                    setFragment(porfolioFragment);
                    return true;
                }else if( item.getItemId() == R.id.menu_team){
                    setFragment(teamFragment);
                    return true;
                }else if( item.getItemId() == R.id.menu_info){
                    setFragment(infoFragment);
                    return true;
                }
                return false;
            }
        });


        setFragment(homeFragment);

        //setPorfolioFragment();
        //setTeamFragment();
        //setCVFragment();
        //setHomeFragment();
        //setHomeFragment();
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_main, fragment);
        fragmentTransaction.commit();
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
            HashMap<String, Object> mapa = new HashMap<>();
            mapa.put(""+keyUsuario, "true");

            FirebaseDatabase.getInstance().getReference()
                    .child("Usuarios")
                    .child(keyUsuario)
                    .updateChildren(mapa);
            //DatabaseReference databaseRefPermissao = FirebaseDatabase.getInstance().getReference().child("Permissao");
        }
    }

    /*
    * equipe - team
    *
    *
    *
    *
    *
    * */
    private void setPorfolioFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new PorfolioFragment()).commit();
    }

    private void setTeamFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new TeamFragment()).commit();
    }
    private void setCVFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new CVFragment()).commit();
    }
    private void setHomeFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new HomeFragment()).commit();
    }
}
