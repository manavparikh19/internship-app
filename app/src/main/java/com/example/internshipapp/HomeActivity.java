package com.example.internshipapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    //This is for Category
    RecyclerView recyclerView;
    String[] categoryNameArray = {"Vegetable", "Fruits", "Berries", "Chilli"};
    int[] categoryImageArray = {R.drawable.vegetables, R.drawable.fruits, R.drawable.berries, R.drawable.chillies};

    ArrayList<CategoryList> arrayList;
    CategoryAdapter adapter;



    //This is for products
    RecyclerView productRecycler;
    String[] productNameArray={"Beet","Green Chilli","Red Chilli","Apple","Banana","Grapes","Blueberry","Strawberry"};
    String[] productPriceArray={"50","20","30","200","40","80","150","100"};
    String[] unitArray={"250g","100g","100g","1kg","12 item","500g","1kg","4 item"};
    int[] productImageArray={R.drawable.beet,R.drawable.green_chilli,R.drawable.red_chilli,R.drawable.apple,R.drawable.bananas,R.drawable.grapes,R.drawable.blueberry,R.drawable.strawberry};

    ArrayList<ProductList> productArrayList;
    ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        for (int i = 0; i < categoryNameArray.length; i++) {
            CategoryList list = new CategoryList();
            list.setName(categoryNameArray[i]);
            list.setImage(categoryImageArray[i]);
            arrayList.add(list);
        }


        //adapter=new CategoryAdapter(HomeActivity.this,categoryNameArray,categoryImageArray);

        adapter = new CategoryAdapter(HomeActivity.this, arrayList);
        recyclerView.setAdapter(adapter);




        productRecycler=findViewById(R.id.home_product_recyclerview);
        productRecycler.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        productRecycler.setItemAnimator(new DefaultItemAnimator());

        productArrayList=new ArrayList<>();
        for (int i=0;i<productNameArray.length;i++){
            ProductList list=new ProductList();
            list.setName(productNameArray[i]);
            list.setPrice(productPriceArray[i]);
            list.setUnit(unitArray[i]);
            list.setImage(productImageArray[i]);
            productArrayList.add(list);
        }
        productAdapter =new ProductAdapter(HomeActivity.this,productArrayList);
        productRecycler.setAdapter(productAdapter);
    }
    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> {
        Context context;
        ArrayList<ProductList> productArrayList;
        public ProductAdapter(HomeActivity homeActivity, ArrayList<ProductList> productArrayList) {
            this.context=homeActivity;
            this.productArrayList=productArrayList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_horizantal,parent,false);
            return new MyHolder(view);
        }

        public class MyHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView name,price;
            public MyHolder(@NonNull  View itemView) {
                super(itemView);
                imageView=itemView.findViewById(R.id.custom_product_horizontal_image);
                name=itemView.findViewById(R.id.custom_product_horizontal_name);
                price=itemView.findViewById(R.id.custom_product_horizontal_price);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull  HomeActivity.ProductAdapter.MyHolder holder, int position) {
            holder.imageView.setImageResource(productArrayList.get(position).getImage() );
            holder.name.setText(productArrayList.get(position).getName());
            holder.price.setText(context.getResources().getString(R.string.price_symbol)+productArrayList.get(position).getPrice()+"/"+productArrayList.get(position).getUnit());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putString("name",productArrayList.get(position).getName());
                    bundle.putString("price",context.getResources().getString(R.string.price_symbol)+productArrayList.get(position).getPrice()+"/"+productArrayList.get(position).getUnit());
                    bundle.putInt("image",productArrayList.get(position).getImage());
                    Intent intent=new Intent(context,ProductDetailActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }

            });
        }

        @Override
        public int getItemCount() {
            return productArrayList.size();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        if (id == R.id.home_menu_message) {
            new CommonMethod(HomeActivity.this, MessageActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder>{
        Context context;
        ArrayList<CategoryList> arrayList;
        public CategoryAdapter(HomeActivity homeActivity, ArrayList<CategoryList> arrayList) {
            this.context=homeActivity;
            this.arrayList=arrayList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);
            return new MyHolder(view);
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;
            public MyHolder(@NonNull  View itemView) {
                super(itemView);
                imageView=itemView.findViewById(R.id.custom_category_image);
                textView=itemView.findViewById(R.id.custom_category_name);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull  HomeActivity.CategoryAdapter.MyHolder holder, int position) {

            holder.imageView.setImageResource(arrayList.get(position).getImage());
            holder.textView.setText(arrayList.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //new CommonMethod(context,ProductActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("category_position",position);
                    Intent intent=new Intent(context,ProductActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }






   /* private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder>{
        Context context;
        String[] categoryNameArray;
        int[] categoryImageArray;
        public CategoryAdapter(HomeActivity homeActivity, String[] categoryNameArray, int[] categoryImageArray) {
            this.context=homeActivity;
            this.categoryNameArray=categoryNameArray;
            this.categoryImageArray=categoryImageArray;

        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);

            return new MyHolder(view);
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView name;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.custom_category_image);
                name = itemView.findViewById(R.id.custom_category_name);
            }
        }
        @Override
        public void onBindViewHolder(@NonNull  HomeActivity.CategoryAdapter.MyHolder holder, int position) {
                holder.name.setText(categoryNameArray[position]);
                holder.imageView.setImageResource(categoryImageArray[position]);

        }

        @Override
        public int getItemCount() {
            return categoryNameArray.length;
        }



    }*/
}