package com.example.smartlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.smartlist.databinding.ActivityMainBinding;
import com.example.smartlist.Adapter.CategoryAdapter;
import com.example.smartlist.Domain.Category;
import com.example.smartlist.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initCategory();
        setVariable();
    }

    private void setVariable() {
        binding.bottomMenu.setItemSelected(R.id.inicio, true);
        binding.bottomMenu.setOnItemSelectedListener(i -> {
            if(i==R.id.carrito){
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            } else if (i==R.id.listas) {
                startActivity(new Intent(MainActivity.this, ListsActivity.class));
            }
        });
    }

    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Category.class));
                    }
                    if (list.size() > 0) {
                        binding.categoryView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                        binding.categoryView.setAdapter(new CategoryAdapter(list));
                    }
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}