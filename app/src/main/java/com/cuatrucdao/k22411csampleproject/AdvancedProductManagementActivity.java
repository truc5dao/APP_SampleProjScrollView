package com.cuatrucdao.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cuatrucdao.adapters.ProductAdapter;
import com.cuatrucdao.models.Customer;
import com.cuatrucdao.models.ListProduct;
import com.cuatrucdao.models.Product;

public class AdvancedProductManagementActivity extends AppCompatActivity {
    ListView lvAdvancedProduct;
    ProductAdapter adapter;
    ListProduct listProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advanced_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addEvents() {
        {
//            lvAdvancedProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                    Product p = adapter.getItem(i);
//                    displayAdvancedProductDetailActivity(p);
//                }
//            });

            lvAdvancedProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    Product p = adapter.getItem(i);
                    if (p == null) {
                        Toast.makeText(AdvancedProductManagementActivity.this, "Product is null!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(AdvancedProductManagementActivity.this, "Clicked: " + p.getName(), Toast.LENGTH_SHORT).show();
                    displayAdvancedProductDetailActivity(p);
                }
            });

        }
    }


    private void displayAdvancedProductDetailActivity(Product p) {
        Intent intent = new Intent(AdvancedProductManagementActivity.this, AdvancedProductDetailActivity.class);
        intent.putExtra("SELECTED_PRODUCT", p);
        startActivity(intent);


    }


    private void addViews() {
        lvAdvancedProduct = findViewById(R.id.lvAdvancedProduct);
        adapter = new ProductAdapter(AdvancedProductManagementActivity.this,
                R.layout.item_advanced_product);
        lvAdvancedProduct.setAdapter(adapter);

        listProduct = new ListProduct();
        listProduct.generate_sample_product_dataset();
        adapter.addAll(listProduct.getProducts());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_product, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_new_product) {
            Toast.makeText(AdvancedProductManagementActivity.this,
                    "Open the screen to add a new product",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AdvancedProductManagementActivity.this, AdvancedProductDetailActivity.class);
            //startActivity(intent);
            // dong goi va dat ma request code la 300
            startActivityForResult(intent, 300);

        } else if (item.getItemId() == R.id.menu_help) {
            Toast.makeText(AdvancedProductManagementActivity.this,
                    "Open website guiding instructions",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}