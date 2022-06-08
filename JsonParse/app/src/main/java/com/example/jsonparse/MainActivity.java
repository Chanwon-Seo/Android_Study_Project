package com.example.jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.jsonparse.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //코드 시작
    ActivityMainBinding binding;
    ArrayList<String> countryList;
    ArrayAdapter<String> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;
    //코드 끝

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        initializeCountrylist();
                new fetchData().start();
        // 코드 끝

    }

    private void initializeCountrylist() {

        countryList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryList);
        binding.regionList.setAdapter(listAdapter);// xml과 연동
    }

    class fetchData extends Thread {

        String data = "";

        @Override
        public void run() {

            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                }
            });

            try {
                URL url = new URL("받아야할 API");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    data = data + line;
                }

                if (!data.isEmpty()) {

                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray regions = jsonObject.getJSONArray("region");
                    countryList.clear();
                    for (int i = 0; i < regions.length(); i++) {
                        JSONObject countrys = regions.getJSONObject(i);
                        String countryResult = countrys.getString("country");
                        countryList.add(countryResult);
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            mainHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    listAdapter.notifyDataSetChanged();
                }
            });

        }
    }


}