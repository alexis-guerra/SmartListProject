package com.example.smartlist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartlist.Domain.CloseProductsDomain;
import com.example.smartlist.R;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.Viewholder> {
    public ProductsAdapter(ArrayList<CloseProductsDomain> closeProductsDomain) {
        this.closeProductsDomains = closeProductsDomain;
    }

    ArrayList<CloseProductsDomain> closeProductsDomains;


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_close_products,parent,false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.title.setText(closeProductsDomains.get(position).getTitle());
        holder.precio.setText(closeProductsDomains.get(position).getPrecio());

        int drawableResourceId= holder.itemView.getContext().getResources().getIdentifier(closeProductsDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return closeProductsDomains.size();
    }

    public  class Viewholder extends RecyclerView.ViewHolder{
        TextView title,precio;
        ImageView pic;
        public Viewholder(@NonNull View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.title);
            pic=itemView.findViewById(R.id.pic);
            precio=itemView.findViewById(R.id.precio);

        }

    }
}
