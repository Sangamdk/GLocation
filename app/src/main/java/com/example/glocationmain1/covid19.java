package com.example.glocationmain1;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class covid19 extends AppCompatActivity {
    private TextView tv_tests, tv_active, tv_deaths,tv_recovered,tv_todaycases,tvLastUpdated;
    private TextView tvw_tests,tvw_cases,tvw_active,tvw_recovered,tvw_deaths,tvw_todaycases,tvw_critical,tvw_todayDeaths,tvw_affectedCountries;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_covid19);

        tv_tests = findViewById(R.id.tv_tests);
        tv_active = findViewById(R.id.tv_active);
        tv_deaths = findViewById(R.id.tv_deaths);
        tv_recovered = findViewById(R.id.tv_recovered);
        tv_todaycases = findViewById(R.id.tv_todaycases);
        tvLastUpdated = findViewById(R.id.tvLastUpdated);

        ////////////////world///////////////
        tvw_tests = findViewById(R.id.tvw_tests);
        tvw_cases = findViewById(R.id.tvw_cases);
        tvw_active = findViewById(R.id.tvw_active);
        tvw_recovered = findViewById(R.id.tvw_recovered);
        tvw_deaths = findViewById(R.id.tvw_deaths);
        tvw_todaycases = findViewById(R.id.tvw_todaycases);
        tvw_critical = findViewById(R.id.tvw_critical);
        tvw_todayDeaths = findViewById(R.id.tvw_todayDeaths);
        tvw_affectedCountries = findViewById(R.id.tvw_affectedCountries);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getData();
        getDataWorld();

    }
    private String getDate(long milliSecond){
        // Mon, 23 Mar 2020 02:01:04 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa");

        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return formatter.format(calendar.getTime());
    }
    private void getData()
    {
        // ReferenceQueue queue= Volley.newRequestQueue(getActionBar());
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://disease.sh/v2/countries/india";
        // String url ="https://disease.sh/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tv_tests.setText(jsonObject.getString("tests"));
                    tv_active.setText(jsonObject.getString("active"));
                    tv_deaths.setText(jsonObject.getString("deaths"));
                    tv_recovered.setText(jsonObject.getString("recovered"));
                    tv_todaycases.setText(jsonObject.getString("todayCases"));
                    tvLastUpdated.setText(getDate(jsonObject.getLong("updated")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error Response", error.toString());
            }
        });


        queue.add(stringRequest);
    }
    private void getDataWorld()
    {
        // ReferenceQueue queue= Volley.newRequestQueue(getActionBar());
        RequestQueue queue = Volley.newRequestQueue(this);

        // String url = "https://disease.sh/v2/countries/india";
        String url ="https://disease.sh/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tvw_tests.setText(jsonObject.getString("tests"));
                    tvw_active.setText(jsonObject.getString("active"));
                    tvw_deaths.setText(jsonObject.getString("deaths"));
                    tvw_recovered.setText(jsonObject.getString("recovered"));
                    tvw_todaycases.setText(jsonObject.getString("todayCases"));
                    tvw_critical.setText(jsonObject.getString("critical"));
                    tvw_todayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvw_affectedCountries.setText(jsonObject.getString("affectedCountries"));
                    tvw_cases.setText(jsonObject.getString("cases"));
                    tvLastUpdated.setText(getDate(jsonObject.getLong("updated")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error Response", error.toString());
            }
        });


        queue.add(stringRequest);
    }
}