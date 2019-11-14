package com.example.datos_colombia_api.datosapi;

import com.example.datos_colombia_api.models.Inventory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DatosAPIservice {

    @GET("8ene-pp26.json")
    Call<ArrayList<Inventory>> getInventoryList();

}
