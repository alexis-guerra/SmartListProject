package com.example.smartlist.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlist.Adapter.ProductsListAdapter;
import com.example.smartlist.Domain.Stores;
import com.example.smartlist.databinding.ActivityListProductsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListProductsActivity extends BaseActivity {
    ActivityListProductsBinding binding;
    private int categoryId;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();

    }
    private void initList() {
        DatabaseReference myRef = database.getReference("Products");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Stores> list = new ArrayList<>();
        Query query = myRef.orderByChild("CategoryId").equalTo(categoryId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue :
                            snapshot.getChildren()) {
                        list.add(issue.getValue(Stores.class));
                    }
                    if (list.size() > 0) {
                        binding.productsListView.setLayoutManager(new LinearLayoutManager(ListProductsActivity.this, LinearLayoutManager.VERTICAL, false));
                        binding.productsListView.setAdapter(new ProductsListAdapter(list));
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", 0);
        categoryName = getIntent().getStringExtra("CategoryName");

        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(v -> finish());
    }

}

