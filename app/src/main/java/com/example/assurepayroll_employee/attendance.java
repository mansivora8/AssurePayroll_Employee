package com.example.assurepayroll_employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class attendance extends AppCompatActivity {
    GridView gridView;
    static final String[] gridviewvalue=new String[]{
            "FaceBook","Instagram","Whatsapp","Snapchat","Telegram","Youtube",">>>"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        gridView=findViewById(R.id.attendance_gridview);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,gridviewvalue);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),((TextView) view).getText()+" is clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}