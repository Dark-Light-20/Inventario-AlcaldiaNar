package com.example.datos_colombia_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.datos_colombia_api.datosapi.DatosAPIservice;
import com.example.datos_colombia_api.models.Inventory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DATOSCOl";

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData();
    }

    private void getData() {
        DatosAPIservice service = retrofit.create(DatosAPIservice.class);
        Call<ArrayList<Inventory>> inventoryAnswerCall = service.getInventoryList();

        inventoryAnswerCall.enqueue(new Callback<ArrayList<Inventory>>() {
            @Override
            public void onResponse(Call<ArrayList<Inventory>> call, Response<ArrayList<Inventory>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Inventory> inventories = response.body();

                    for(int i=0;i<inventories.size();i++){
                        Inventory inventory = inventories.get(i);
                        Log.i(TAG, "Equipo #"+inventory.getId()+": "
                        +inventory.getPropietario()+", "+inventory.getDescripcionEquipo());
                    }
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
}
