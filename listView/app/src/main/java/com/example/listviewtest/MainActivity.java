package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private ListView listView;

    ArrayList<String> countryName = new ArrayList<>();
    ArrayList<String> regionCount = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryName.add("1-1");
        countryName.add("1-2");

        regionCount.add("2-1");
        regionCount.add("2-2");

        adapter = new MyAdapter();
        listView = (ListView) findViewById(R.id.listView);

        InitializeCountryData();

        listView.setAdapter(adapter);
    }

    private void InitializeCountryData() {
        for (int i = 0; i < countryName.length(); i++) {
            
        }

    }

}