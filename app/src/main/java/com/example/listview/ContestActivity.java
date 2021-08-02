package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ContestActivity extends AppCompatActivity {
    ArrayList<ContestModel> contestModels = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.ContestView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String siteName = intent.getStringExtra(MainActivity.NAME_KEY);


        String url;

        if (siteName.equals("CodeForces")) {
            url = "https://www.kontests.net/api/v1/codeforces";
        } else if (siteName.equals("CodeChef")) {
            url = "https://www.kontests.net/api/v1/code_chef";
        } else if (siteName.equals("TopCoder")) {
            url = "https://www.kontests.net/api/v1/top_coder";
        } else if (siteName.equals("AtCoder") ) {
            url = "https://www.kontests.net/api/v1/at_coder";
        } else if (siteName.equals("CSAcademy")) {
            url = "https://www.kontests.net/api/v1/cs_academy";
        } else if (siteName.equals("HackerRank")) {
            url = "https://www.kontests.net/api/v1/hacker_rank";
        } else if (siteName.equals("HackerEarth")) {
            url = "https://www.kontests.net/api/v1/hacker_earth";
        } else if (siteName.equals("LeetCode") ) {
            url = "https://www.kontests.net/api/v1/leet_code";
        } else {
            url = "https://www.kontests.net/api/v1/codeforces";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray jsonArray = new JSONArray(response);

                    if (jsonArray.length() > 0) {
                        for (int i=0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String url = jsonObject.getString("url");
                            contestModels.add(new ContestModel(name,url));
                        }

                    }

                    CustomAdapter customAdapter = new CustomAdapter(contestModels);
                    recyclerView.setAdapter(customAdapter);



                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Unwanted error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}