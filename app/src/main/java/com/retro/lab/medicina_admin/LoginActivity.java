package com.retro.lab.medicina_admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.retro.lab.medicina_admin.Models.AdminModel;
import com.retro.lab.medicina_admin.Services.SharedPref;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton login;
    AppCompatEditText appCompatEditText, appCompatEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appCompatEditText = findViewById(R.id.appCompatEditText);
        appCompatEditText2 = findViewById(R.id.appCompatEditText2);

        login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginAdmin(appCompatEditText.getText().toString(),
                        appCompatEditText2.getText().toString());

            }
        });

    }

    public void loginAdmin(String username, String password){

        StringRequest request = new StringRequest(Request.Method.POST, URLS.URL_LOGIN_ADMIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("error")){
                        Toast.makeText(getApplicationContext(), "response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {

                        JSONObject object1 = object.getJSONObject("user");

                        Gson gson = new Gson();
                        AdminModel admin = gson.fromJson(object1.toString(), AdminModel.class);

                        SharedPref.getInstance(getApplicationContext()).userSignup(admin);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }catch (Exception exception){
                    Toast.makeText(getApplicationContext(), "Exception: " + exception, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

}