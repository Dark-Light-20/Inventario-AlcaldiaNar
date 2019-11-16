package com.example.datos_colombia_api.recyclerView;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datos_colombia_api.R;
import com.example.datos_colombia_api.models.Inventory;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {

    private ArrayList<Inventory> inventories;
    private Context context;
    private Dialog dialog;

    public Adapter(Context context) {
        this.context = context;
        this.inventories = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final Holder holder = new Holder(view);

        holder.getItemCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.item_detail);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                ImageView imageView = dialog.findViewById(R.id.item_image_detail);
                TextView type = dialog.findViewById(R.id.item_type_detail);
                TextView brand = dialog.findViewById(R.id.item_brand_detail);
                TextView status = dialog.findViewById(R.id.item_status_detail);
                TextView location = dialog.findViewById(R.id.item_location_detail);

                String typeTxt = context.getResources().getString(R.string.type)+" "+
                        inventories.get(holder.getAdapterPosition()).getTipo();
                String brandTxt = context.getResources().getString(R.string.brand)+" "+
                        inventories.get(holder.getAdapterPosition()).getMarca();
                String statusTxt = context.getResources().getString(R.string.status)+" "+
                        inventories.get(holder.getAdapterPosition()).getEstado();
                String locationTxt = context.getResources().getString(R.string.location)+" "+
                        inventories.get(holder.getAdapterPosition()).getUbicacion();

                type.setText(typeTxt);
                brand.setText(brandTxt);
                status.setText(statusTxt);
                location.setText(locationTxt);
                setImage(holder.getDescription().getText().toString(), imageView);

                dialog.show();
                Toast.makeText(context, "Item #: "+String.valueOf(holder.getAdapterPosition()+1), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
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
        setImage(inventories.get(position).getDescripcionEquipo(), imageView);
    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }

    public void addInventories(ArrayList<Inventory> inventories) {
        this.inventories.addAll(inventories);
        notifyDataSetChanged();
    }

    private void setImage(String description, ImageView imageView) {
        switch (description) {
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
}
