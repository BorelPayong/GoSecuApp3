package com.test1.gosecuapp3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.test1.gosecuapp3.model.Employe;
import com.test1.gosecuapp3.model.Materiel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String Json_Url2 = "https://raw.githubusercontent.com/Alexon1999/MSPR_JAVA-APP/master/web/data.json";

    private static List<Employe> lesEmployes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        extractUser();

        TextView username =(TextView) findViewById(R.id.etName);
        TextView password =(TextView) findViewById(R.id.etPassword);

        Button loginbtn = (Button) findViewById(R.id.btnLogin);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employe emp = Employe.logIn(lesEmployes,username.getText().toString(), password.getText().toString());
                if(emp != null){
                    //correct
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MaterielActivity.class);
                    // TODO: passer les matériels dans l'autre écran emp.getMaterielsEmpruntes()
                    startActivity(intent);
                }else
                    //incorrect
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void extractUser() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Json_Url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject emp = response.getJSONObject(i);
                        Employe employe = new Employe(emp.getString("nom"), emp.getString("prenom"), emp.getString("imgUrl"), emp.getString("role"), emp.getString("mdp"));
                        JSONArray materilsJsonArray = emp.getJSONArray("materielsEmpruntes");
                        for (int j = 0; j < materilsJsonArray.length(); j++) {
                            JSONObject mat = materilsJsonArray.getJSONObject(j);
                            employe.addMateriel(new Materiel(mat.getString("code"), mat.getString("label")));
                        }
                        lesEmployes.add(employe);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // System.out.println(lesEmployes);
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