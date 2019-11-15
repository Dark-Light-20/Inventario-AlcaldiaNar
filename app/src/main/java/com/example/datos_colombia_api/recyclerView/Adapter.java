package com.example.datos_colombia_api.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView description = holder.getDescription();
        TextView id = holder.getId();
        TextView owner = holder.getOwner();
        ImageView imageView = holder.getImageView();

        String txtId = "#"+inventories.get(position).getId()+": ";

        description.setText(inventories.get(position).getDescripcionEquipo());
        id.setText(txtId);
        owner.setText(inventories.get(position).getPropietario());
        switch (inventories.get(position).getDescripcionEquipo()) {
            case "Computador":
                imageView.setBackgroundResource(R.drawable.computer);
                break;
            case "Monitor":
                imageView.setBackgroundResource(R.drawable.monitor);
                break;
            case "Impresora":
                imageView.setBackgroundResource(R.drawable.printer);
                break;
            case "Fotocopiadora":
                imageView.setBackgroundResource(R.drawable.photocopier);
                break;
            case "Escaner":
                imageView.setBackgroundResource(R.drawable.scanner);
                break;
        }
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
