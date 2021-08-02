package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String NAME_KEY = "com.example.listview.name.key";
    ListView listView;
    String[] arr = {"CodeForces",
            "CodeChef",
            "toph",
            "TopCoder",
            "AtCoder",
            "CSAcademy",
            "HackerRank",
            "HackerEarth",
            "LeetCode",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        Intent intent = new Intent(this, ContestActivity.class);
        MyAdapter myAdapter = new MyAdapter(this, R.layout.my_layout, arr);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arr[position], Toast.LENGTH_SHORT).show();
                intent.putExtra(NAME_KEY,arr[position]);
                startActivity(intent);
            }
        });

    }
}