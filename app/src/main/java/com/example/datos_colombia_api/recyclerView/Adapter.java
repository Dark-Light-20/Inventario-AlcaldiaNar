package com.example.datos_colombia_api.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datos_colombia_api.R;
import com.example.datos_colombia_api.models.Inventory;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {

    private ArrayList<Inventory> inventories;

    public Adapter() {
        this.inventories = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.description.setText(inventories.get(position).getDescripcionEquipo());
        holder.id.setText(inventories.get(position).getId());
        holder.owner.setText(inventories.get(position).getPropietario());
        // do "if" for image // import generic images
    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }

    public void addInventories(ArrayList<Inventory> inventories) {
        this.inventories.addAll(inventories);
        notifyDataSetChanged();
    }
}
