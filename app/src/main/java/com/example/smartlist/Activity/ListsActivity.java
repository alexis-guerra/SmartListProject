package com.example.smartlist.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import com.example.smartlist.R;
import com.example.smartlist.databinding.ListItemsBinding;

public class ListsActivity extends AppCompatActivity {
    ListItemsBinding binding = ListItemsBinding.inflate(getLayoutInflater());
    ListView listView = findViewById(R.id.listview);

    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ListItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataArrayList = new ArrayList<>();
        populateListData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void populateListData() {
        int[] imageList = {
                R.drawable.pasta, R.drawable.maggi, R.drawable.cake,
                R.drawable.pancake, R.drawable.pizza, R.drawable.burger,
                R.drawable.fries
        };

        int[] descList = {
                R.string.pastaDesc, R.string.maggieDesc, R.string.cakeDesc,
                R.string.pancakeDesc, R.string.pizzaDesc, R.string.burgerDesc,
                R.string.friesDesc
        };
        String[] nameList = {
                "Pasta", "Maggi", "Cake", "Pancake", "Pizza", "Burgers", "Fries"
        };

        for (int i = 0; i < imageList.length; i++) {
            ListData listData = new ListData(
                    nameList[i], descList[i], imageList[i]
            );
            dataArrayList.add(listData);
        }

        listAdapter = new com.example.smartlist.Adapter.ListAdapter(ListsActivity.this, dataArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListsActivity.this, DetailedListActivity.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("desc", descList[i]);
                intent.putExtra("image", imageList[i]);
                startActivity(intent);
            }
        });
    }
}