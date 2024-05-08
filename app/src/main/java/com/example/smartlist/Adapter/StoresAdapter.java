package com.example.smartlist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartlist.Domain.CloseStoresDomain;
import com.example.smartlist.R;

import java.util.ArrayList;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.ViewHolder> {
    ArrayList<CloseStoresDomain> closeStoresDomains;

    public StoresAdapter(ArrayList<CloseStoresDomain> closeStoresDomains) {
        this.closeStoresDomains = closeStoresDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(closeStoresDomains.get(position).getTitle());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "sterenlogo";
                break;
            }
            case 1: {
                picUrl = "walmartlogoremoveb";
                break;
            }
            case 2: {
                picUrl = "costcoremoveb";
                break;
            }
            case 3: {
                picUrl = "bestbuylogo";
                break;
            }
        }
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.categoryPic);
    }


    @Override
    public int getItemCount() {
        return closeStoresDomains.size();
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
    TextView categoryName;
    ImageView categoryPic;
    ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName= itemView.findViewById(R.id.categoryName);
            categoryPic=itemView.findViewById(R.id.categoryPic);
        }
    }
}
