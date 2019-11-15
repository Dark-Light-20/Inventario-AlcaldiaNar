package com.example.datos_colombia_api.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datos_colombia_api.R;

class Holder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    TextView description, id, owner;

    Holder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.item_image);
        description = itemView.findViewById(R.id.item_description);
        id = itemView.findViewById(R.id.item_id);
        owner = itemView.findViewById(R.id.item_owner);
    }
}
