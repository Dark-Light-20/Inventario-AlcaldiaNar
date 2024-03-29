package com.example.datos_colombia_api.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datos_colombia_api.R;

class Holder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView description, id, owner;
    private CardView itemCardView;

    Holder(@NonNull View itemView) {
        super(itemView);

        itemCardView = itemView.findViewById(R.id.item);
        imageView = itemView.findViewById(R.id.item_image);
        description = itemView.findViewById(R.id.item_description);
        id = itemView.findViewById(R.id.item_id);
        owner = itemView.findViewById(R.id.item_owner);
    }

    public CardView getItemCardView() {
        return itemCardView;
    }

    public void setItemCardView(CardView itemCardView) {
        this.itemCardView = itemCardView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public TextView getId() {
        return id;
    }

    public void setId(TextView id) {
        this.id = id;
    }

    public TextView getOwner() {
        return owner;
    }

    public void setOwner(TextView owner) {
        this.owner = owner;
    }

}
