package com.example.smartlist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.smartlist.Adapter.ProductsAdapter;
import com.example.smartlist.Adapter.StoresAdapter;
import com.example.smartlist.Domain.CloseProductsDomain;
import com.example.smartlist.Domain.CloseStoresDomain;
import com.example.smartlist.R;

import java.util.ArrayList;

import dalvik.annotation.optimization.FastNative;

public class MainActivity extends AppCompatActivity{
private RecyclerView.Adapter adapter,adapter2;
private RecyclerView recyclerViewCategory,recyclerViewProductsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.smartlist.R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        recyclerViewCategory();
        recyclerViewProductsList();
    }

    private void recyclerViewProductsList() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewProductsList=findViewById(com.example.smartlist.R.id.view2);
        recyclerViewProductsList.setLayoutManager(linearLayoutManager);

        ArrayList<CloseProductsDomain>closeProductsDomains=new ArrayList<>();
        closeProductsDomains.add(new CloseProductsDomain("Laptop","laptop","5000"));
        closeProductsDomains.add(new CloseProductsDomain("Computadora","desktop","15000"));

        adapter2=new ProductsAdapter(closeProductsDomains);
        recyclerViewProductsList.setAdapter(adapter2);
    }

    private void recyclerViewCategory(){
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
    recyclerViewCategory=findViewById(com.example.smartlist.R.id.view1);
    recyclerViewCategory.setLayoutManager(linearLayoutManager);

    ArrayList<CloseStoresDomain>categoryList=new ArrayList<>();
    categoryList.add(new CloseStoresDomain("Steren","sterenlogo"));
    categoryList.add(new CloseStoresDomain("Walmart","walmartlogoremoveb"));
    categoryList.add(new CloseStoresDomain("Costco","costcoremoveb"));
    categoryList.add(new CloseStoresDomain("BestBuy","bestbuylogo"));

    adapter=new StoresAdapter(categoryList);
    recyclerViewCategory.setAdapter(adapter);
}

}