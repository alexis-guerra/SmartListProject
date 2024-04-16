package com.example.smartlist.Activity;

import static android.os.Build.VERSION_CODES.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.SearchView;

import com.example.smartlist.Adapter.CategoryAdapter;
import com.example.smartlist.Domain.CategoryDomain;
import com.example.smartlist.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
private RecyclerView.Adapter adapter,adapter2;
private RecyclerView recyclerViewCategory,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.smartlist.R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        recyclerViewCategory();
    }
private void recyclerViewCategory(){
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
    recyclerViewCategory=findViewById(com.example.smartlist.R.id.view1);
    recyclerViewCategory.setLayoutManager(linearLayoutManager);

    ArrayList<CategoryDomain>categoryList=new ArrayList<>();
    categoryList.add(new CategoryDomain("Pizza","cat_1"));
    categoryList.add(new CategoryDomain("Hamburguesa","cat_2"));
    categoryList.add(new CategoryDomain("Pollo","cat_3"));
    categoryList.add(new CategoryDomain("Hotdogs","cat_4"));

    adapter=new CategoryAdapter(categoryList);
    recyclerViewCategory.setAdapter(adapter);
}

}