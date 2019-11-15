package com.example.datos_colombia_api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.datos_colombia_api.datosapi.DatosAPIservice;
import com.example.datos_colombia_api.models.Inventory;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "DATACOl";

    private Retrofit retrofit;

    private ArrayList<Inventory> inventories;

    private AboutFragment aboutFragment;
    private InventoryFragment inventoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutFragment = new AboutFragment();
        inventoryFragment = new InventoryFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    inventoryFragment).commit();
            navigationView.setCheckedItem(R.id.inventory_item);
        }
    }

    private void getData() {
        DatosAPIservice service = retrofit.create(DatosAPIservice.class);
        Call<ArrayList<Inventory>> inventoryAnswerCall = service.getInventoryList();

        inventoryAnswerCall.enqueue(new Callback<ArrayList<Inventory>>() {
            @Override
            public void onResponse(Call<ArrayList<Inventory>> call, Response<ArrayList<Inventory>> response) {
                if (response.isSuccessful()) {
                    inventories = response.body();

                    for(int i=0;i<inventories.size();i++){
                        Inventory inventory = inventories.get(i);
                        Log.i(TAG, "Equipo #"+inventory.getId()+": "
                        +inventory.getPropietario()+", "+inventory.getDescripcionEquipo());
                    }

                    inventoryFragment.adapter.addInventories(inventories);
                }
                else {
                    Log.e(TAG, " onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inventory>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.about_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        aboutFragment).commit();
                getSupportActionBar().setTitle(getString(R.string.about));
                break;

            case R.id.inventory_item:
                getData();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        inventoryFragment).commit();
                getSupportActionBar().setTitle(getString(R.string.inventory));
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
