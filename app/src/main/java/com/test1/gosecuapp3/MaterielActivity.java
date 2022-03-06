package com.test1.gosecuapp3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MaterielActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Materiel> materiels;
    Adapter adapter;

    private static String Json_Url = "https://run.mocky.io/v3/853e8ed9-570c-44da-b7b4-795000c73de3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiel);

        recyclerView = findViewById(R.id.listeMaterielRecycle);
        materiels = new ArrayList<>();

        extractMateriel();
    }

    private void extractMateriel() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Json_Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);

                        Materiel materiel = new Materiel();
                        materiel.setMateriel(songObject.getString("materiel").toString());
                        materiel.setRole(songObject.getString("role".toString()));
                        materiel.setCoverImage(songObject.getString("image"));
                        materiels.add(materiel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),materiels);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            /**
             * Callback method that an error has been occurred with the provided error code and optional
             * user-readable message.
             *
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }
}