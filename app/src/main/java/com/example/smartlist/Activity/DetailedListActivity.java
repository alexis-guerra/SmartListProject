package com.example.smartlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.smartlist.R;

import com.example.smartlist.databinding.ActivityListDetailedBinding;

public class DetailedListActivity extends AppCompatActivity {

    ActivityListDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            int desc = intent.getIntExtra("desc", R.string.maggieDesc);
            int image = intent.getIntExtra("image", R.drawable.maggi);

            binding.detailName.setText(name);
            binding.detailDesc.setText(desc);
            binding.detailImage.setImageResource(image);
        }
    }
}