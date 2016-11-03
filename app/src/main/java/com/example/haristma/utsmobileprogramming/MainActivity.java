package com.example.haristma.utsmobileprogramming;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.haristma.utsmobileprogramming.R;

import java.util.ArrayList;
import java.util.List;
import com.example.haristma.utsmobileprogramming.utils.ProductListAdapter;
import com.example.haristma.utsmobileprogramming.utils.SessionManagement;

public class MainActivity extends AppCompatActivity {

    SessionManagement session;
    ListView productListView;
    List<Product> products;
    ProductListAdapter productListAdapter;

    public static int[] image = {
            R.drawable.blackforest,
            R.drawable.greentea,
            R.drawable.keju,
            R.drawable.pandan,
            R.drawable.stoberi
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();

        setProduct();

        productListView = (ListView) findViewById(R.id.list_product);
        productListAdapter = new ProductListAdapter(getApplicationContext(), products, image);
        productListView.setAdapter(productListAdapter);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Product product = (Product)adapterView.getItemAtPosition(position);
                String price = String.valueOf(product.getPrice());

                Intent iDetail = new Intent(getApplicationContext(), Pemesanan.class);
                iDetail.putExtra("position", position);
                iDetail.putExtra("nama",product.getName());
                startActivity(iDetail);
            }
        });
    }

    private void setProduct(){

        Product product1 = new Product(1,"Blackforest","Kekuatan cokelat yang luar biasa",35000);
        Product product2 = new Product(2,"Green Tea","Kue dengan teh hijau alami",85000);
        Product product3 = new Product(3,"Cheese","Kue rasa Keju yang melted",75000);
        Product product4 = new Product(4,"Pandan","Kue dengan aroma pandan alami",60000);
        Product product5 = new Product(5,"Strawberry","Kue  kukus dengan rasa strawberry segar",65000);

        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                session.logoutUser();
                break;
        }
        return true;
    }
}
