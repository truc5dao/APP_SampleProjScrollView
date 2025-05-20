package com.cuatrucdao.k22411csampleproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cuatrucdao.models.Category;
import com.cuatrucdao.models.ListCategory;
import com.cuatrucdao.models.Product;

public class ProductManagementActivity extends AppCompatActivity {
    Spinner spinnerCategory;
    // do su dung nhieu adapter, dat ten cu the cho khong bi nham lan
    ArrayAdapter<Category>adapterCategory;
    ListCategory listCategory;

    ListView lvProduct;
    ArrayAdapter<Product>adapterProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Category c = adapterCategory.getItem(i);
                displayProductByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void displayProductByCategory(Category c) {
        // xoa du lieu cu trong listview
        adapterProduct.clear();
        // nap moi lai du lieu
        adapterProduct.addAll(c.getProducts());
    }

    private void addViews(){
        spinnerCategory = findViewById(R.id.spinnerCategory);
        adapterCategory = new ArrayAdapter<>(ProductManagementActivity.this,
                android.R.layout.simple_spinner_item);

        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory = new ListCategory();
        listCategory.generate_sample_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct = findViewById(R.id.lvProduct);
        adapterProduct = new ArrayAdapter<>(
                ProductManagementActivity.this, android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);

        
    }
}