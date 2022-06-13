package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.retro.lab.medicina_admin.Adapters.UserAdapter;
import com.retro.lab.medicina_admin.Models.UserModel;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserShowActivity extends AppCompatActivity {

    RecyclerView recyclerUser;
    private UserAdapter mExampleAdapter;
    private ArrayList<UserModel> mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_show);

        recyclerUser =findViewById(R.id.recyclerUser);
        recyclerUser.setHasFixedSize(true);
        recyclerUser.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mExampleAdapter = new UserAdapter(this, mExampleList);
        recyclerUser.setAdapter(mExampleAdapter);

        getUsers();

    }

    public void getUsers(){

        StringRequest request = new StringRequest(Request.Method.GET, URLS.URL_GET_All_USERS,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mExampleList.clear();
                mExampleAdapter.notifyDataSetChanged();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("row");

                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        UserModel emg = gson.fromJson(jsonobject.toString(), UserModel.class);
                        mExampleList.add(emg);
                    }
                    mExampleAdapter.notifyDataSetChanged();
                } catch (Exception exception) {
                    Toast.makeText(UserShowActivity.this, "Exception: " + exception, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Response:  " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

}