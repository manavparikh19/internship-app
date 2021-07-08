package com.example.internshipapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    RecyclerView productRecycler;
    ArrayList<ProductList> arrayList;
    ProductAdapter adapter;

    String[] productNameArray0={"Beet"};
    String[] productPriceArray0={"50"};
    String[] unitArray0={"250g"};
    int[] productImageArray0={R.drawable.beet};

    String[] productNameArray1={"Apple","Banana","Grapes"};
    String[] productPriceArray1={"200","40","80"};
    String[] unitArray1={"1kg","12 item","500g"};
    int[] productImageArray1={R.drawable.apple,R.drawable.bananas,R.drawable.grapes};

    String[] productNameArray2={"Blueberry","Strawberry"};
    String[] productPriceArray2={"150","100"};
    String[] unitArray2={"1kg","4 item"};
    int[] productImageArray2={R.drawable.blueberry,R.drawable.strawberry};

    String[] productNameArray3={"Green Chilli","Red Chilli"};
    String[] productPriceArray3={"20","30"};
    String[] unitArray3={"100g","100g"};
    int[] productImageArray3={R.drawable.green_chilli,R.drawable.red_chilli};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setTitle("Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle=getIntent().getExtras();
        int iCategoryPosition=bundle.getInt("category_position");

        productRecycler=findViewById(R.id.product_recycler);
        productRecycler.setLayoutManager(new LinearLayoutManager(ProductActivity.this));
        productRecycler.setItemAnimator(new DefaultItemAnimator());

        arrayList=new ArrayList<>();
        if(iCategoryPosition==0) {

            for (int i = 0; i < productNameArray0.length; i++) {
                ProductList list = new ProductList();
                list.setName(productNameArray0[i]);
                list.setPrice(productPriceArray0[i]);
                list.setUnit(unitArray0[i]);
                list.setImage(productImageArray0[i]);
                arrayList.add(list);
            }
        }
        if(iCategoryPosition==1) {

            for (int i = 0; i < productNameArray1.length; i++) {
                ProductList list = new ProductList();
                list.setName(productNameArray1[i]);
                list.setPrice(productPriceArray1[i]);
                list.setUnit(unitArray1[i]);
                list.setImage(productImageArray1[i]);
                arrayList.add(list);
            }
        }
        if(iCategoryPosition==2) {

            for (int i = 0; i < productNameArray2.length; i++) {
                ProductList list = new ProductList();
                list.setName(productNameArray2[i]);
                list.setPrice(productPriceArray2[i]);
                list.setUnit(unitArray2[i]);
                list.setImage(productImageArray2[i]);
                arrayList.add(list);
            }
        }
        if(iCategoryPosition==3) {

            for (int i = 0; i < productNameArray3.length; i++) {
                ProductList list = new ProductList();
                list.setName(productNameArray3[i]);
                list.setPrice(productPriceArray3[i]);
                list.setUnit(unitArray3[i]);
                list.setImage(productImageArray3[i]);
                arrayList.add(list);
            }
        }

        adapter = new ProductAdapter(ProductActivity.this,arrayList);
        productRecycler.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductActivity.ProductAdapter.MyHolder> {
        Context context;
        ArrayList<ProductList> productArrayList;
        public ProductAdapter(ProductActivity homeActivity, ArrayList<ProductList> productArrayList) {
            this.context=homeActivity;
            this.productArrayList=productArrayList;
        }

        @NonNull
        @Override
        public ProductActivity.ProductAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product,parent,false);
            return new ProductActivity.ProductAdapter.MyHolder(view);
        }

        public class MyHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView name,price;
            public MyHolder(@NonNull  View itemView) {
                super(itemView);
                imageView=itemView.findViewById(R.id.custom_product_image);
                name=itemView.findViewById(R.id.custom_product_name);
                price=itemView.findViewById(R.id.custom_product_price);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull  ProductActivity.ProductAdapter.MyHolder holder, int position) {
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

}