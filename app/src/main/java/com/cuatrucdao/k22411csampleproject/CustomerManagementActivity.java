package com.cuatrucdao.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cuatrucdao.connectors.CustomerConnector;
import com.cuatrucdao.models.Customer;


public class CustomerManagementActivity extends AppCompatActivity {

    ListView lvCustomer;
    ArrayAdapter<Customer> adapter;
    CustomerConnector connector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void displayCustomerDetailActivity(Customer c) {
        Intent intent = new Intent(CustomerManagementActivity.this, CustomerDetailActivity.class);
        intent.putExtra("SELECTED_CUSTOMER", c);
        startActivity(intent);
    }

    private void addViews() {
        lvCustomer = findViewById(R.id.lvCustomer);
        adapter = new ArrayAdapter<>(
                CustomerManagementActivity.this,
                android.R.layout.simple_list_item_1 );
        connector = new CustomerConnector();
        adapter.addAll(connector.get_all_customers());
        lvCustomer.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_customer, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId()==R.id.menu_new_customer)
        {
            Toast.makeText(CustomerManagementActivity.this,
                    "Open the screen to add a new customer",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CustomerManagementActivity.this, CustomerDetailActivity.class);
            //startActivity(intent);
            // dong goi va dat ma request code la 300
            startActivityForResult(intent, 300);

        }
        else if(item.getItemId()==R.id.menu_broadcast_advertising)
        {
            Toast.makeText(CustomerManagementActivity.this,
                    "Send multiple ads to customers",
                    Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId()==R.id.menu_help)
        {
            Toast.makeText(CustomerManagementActivity.this,
                    "Open website guiding instructions",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // xu ly cho customer chi quan tam 300 va 500 do dinh nghia
        if (requestCode==300 && resultCode==500)
        {
            // lay goi tin ra:
            Customer c = (Customer) data.getSerializableExtra("NEW_CUSTOMER");
            process_save_customer(c);
        }
    }

    private void process_save_customer(Customer c) {
        boolean result = connector.isExist(c);
        if (result==true)
        {
            // customer da ton tai, sinh vien tu xu ly truong hop sua thong tin
        }
        else
        {
            // customer chua ton tai, xu ly them moi customer
            connector.addCustomer(c);
            adapter.clear();
            adapter.addAll(connector.get_all_customers());
        }
    }

    private void addEvents()
//    {
//        lvCustomer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
//                Customer selected = adapter.getItem(i);
//                adapter.remove(selected);
//                return false;
//
//
//            }
//
//
//        });
//
//
//    }

    {
        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Customer c = adapter.getItem(i);
                displayCustomerDetailActivity(c);
            }
        });
    }


}