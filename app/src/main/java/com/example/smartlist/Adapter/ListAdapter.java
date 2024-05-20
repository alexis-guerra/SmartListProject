package com.example.smartlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.smartlist.R;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartlist.Activity.ListData;

public class ListAdapter extends ArrayAdapter<ListData> {

    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_items, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        ImageView listImage = convertView.findViewById(R.id.listImage);
        TextView listName = convertView.findViewById(R.id.listName);


        if (listData != null) {
            listImage.setImageResource(listData.getImage());
            listName.setText(listData.getName());

        }

        return convertView;
    }
}
