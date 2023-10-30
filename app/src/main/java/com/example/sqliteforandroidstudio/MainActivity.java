package com.example.sqliteforandroidstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // references to buttons and other controls on the layout
    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    SwitchCompat sw_activeCustomer;
    ListView lv_customerList;

    DataBaseHelper dbHelp;
    ArrayAdapter arAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_Add);
        btn_viewAll = findViewById(R.id.btn_ViewAll);
        et_name = findViewById(R.id.et_Name);
        et_age = findViewById(R.id.et_Age);
        sw_activeCustomer = findViewById(R.id.sw_Active);
        lv_customerList = findViewById(R.id.lv_customerList);

        dbHelp = new DataBaseHelper(MainActivity.this);
        ShowCustomerOnListView(dbHelp);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel custmodl;
                try {
                    custmodl = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked());
                    Toast.makeText(MainActivity.this, custmodl.toString(), Toast.LENGTH_SHORT).show();

                }
                catch(Exception ex){
                    custmodl = new CustomerModel(-1, "error", 0, false);
                    Toast.makeText(MainActivity.this, "Ошибка при создании клиента", Toast.LENGTH_SHORT).show();
                }

                DataBaseHelper dbHelp = new DataBaseHelper(MainActivity.this);
                boolean succes = dbHelp.addOne(custmodl);

                //Toast.makeText(MainActivity.this, "Succes = " + succes, Toast.LENGTH_SHORT).show();
                ShowCustomerOnListView(dbHelp);
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dbHelp = new DataBaseHelper(MainActivity.this);

                // что такое simple_list_item_1
                ShowCustomerOnListView(dbHelp);

                //Toast.makeText(MainActivity.this, "everyone", Toast.LENGTH_SHORT).show();
            }
        });
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dbHelp.deleteOne(clickedCustomer);
                ShowCustomerOnListView(dbHelp);
                Toast.makeText(MainActivity.this, "deleted" + clickedCustomer, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowCustomerOnListView(DataBaseHelper dbHelp) {
        arAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dbHelp.getEveryone());
        lv_customerList.setAdapter(arAdapter);
    }
}