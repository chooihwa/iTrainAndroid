package com.itrainasia.material;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;

    public ViewHolder(LayoutInflater inflater, ViewGroup parent){
        super(inflater.inflate(R.layout.cardview,parent,false));
        imageView=itemView.findViewById(R.id.card_image);
        textView=itemView.findViewById(R.id.title);


    }

}
