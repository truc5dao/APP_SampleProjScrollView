package com.cuatrucdao.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cuatrucdao.models.Product;

public class AdvancedProductDetailActivity extends AppCompatActivity {

    EditText edt_product_id;
    EditText edt_product_name;
    EditText edt_product_quantity;
    EditText edt_product_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advanced_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {

        edt_product_id = findViewById(R.id.edt_product_id);
        edt_product_name = findViewById(R.id.edt_product_name);
        edt_product_quantity= findViewById(R.id.edt_product_quantity);
        edt_product_price = findViewById(R.id.edt_product_price);


        display_infor();
    }

    private void display_infor() {
        // lay intent tu AdvancedProductManagementActivity gui qua
        Intent intent = getIntent();
        // lay du lieu selected product tu intent
        Product p = (Product) intent.getSerializableExtra("SELECTED_PRODUCT");
        if (p == null)
            return;
        // hien thi thong tin product
        edt_product_id.setText(p.getId()+"");
        edt_product_name.setText(p.getName());
        edt_product_quantity.setText(String.valueOf(p.getQuantity()));
        edt_product_price.setText(String.valueOf(p.getPrice()));

    }
}