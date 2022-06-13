package com.retro.lab.medicina_admin.Adapters;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.retro.lab.medicina_admin.MedicinesActivity;
import com.retro.lab.medicina_admin.Models.MedicinesModel;
import com.retro.lab.medicina_admin.PharmaciesActivity;
import com.retro.lab.medicina_admin.R;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;
import com.retro.lab.medicina_admin.UpdateMedicineActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.ExampleviewHolder> {
    private Context mcontex;
    private ArrayList<MedicinesModel> mExamplelist;

    ///lisner for on click
    private  OnItemClickListener mListener;

    public interface  OnItemClickListener{

        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public MedicinesAdapter(Context contex , ArrayList<MedicinesModel> examplelist){
        mcontex=contex;
        mExamplelist=examplelist;

    }

    @NonNull
    @Override
    public ExampleviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontex).inflate(R.layout.medicine_card,parent,false);
        return  new ExampleviewHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExampleviewHolder holder, int position) {

        MedicinesModel currentItem=mExamplelist.get(position);
//        MediaModel mediaModel = mMediaList.get(position);

        int id = currentItem.getId();
        String Name =currentItem.getName();
        String Price = currentItem.getPrice();
        String Pharmacy = currentItem.getPharmacy_name();
        String Address = currentItem.getAddress();
        String Serial = currentItem.getSerial();


        holder.mName.setText(Name);
        holder.mPharmacy.setText(Pharmacy);
        holder.mAddress.setText(Address);
        holder.mPrice.setText("Price: " + Price+" $");

        holder.updateMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext().getApplicationContext(), UpdateMedicineActivity.class);
                intent.putExtra("Med_id", id);
                intent.putExtra("Med_Name", Name);
                intent.putExtra("Ph_Name", Pharmacy);
                intent.putExtra("Ph_Address", Address);
                intent.putExtra("Med_price", Price);
                intent.putExtra("Med_Serial", Serial);
                v.getContext().startActivity(intent);
            }
        });

        holder.deleteMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagNew(v, id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }

    public class ExampleviewHolder extends  RecyclerView.ViewHolder{

        public TextView mName, mPrice, mPharmacy, mAddress;
        public AppCompatButton updateMed, deleteMed;

        public ExampleviewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.mName);
            mPrice=itemView.findViewById(R.id.mPrice);
            mPharmacy = itemView.findViewById(R.id.mPharmacy);
            mAddress = itemView.findViewById(R.id.mAddress);

            updateMed = itemView.findViewById(R.id.updateMedicine);
            deleteMed = itemView.findViewById(R.id.deleteMedicine);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int postion = getAdapterPosition();
                            mListener.onItemClick(postion);
                    }
                }
            });
        }
    }

    public void diagNew(View view, int id){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setMessage("Are You Sure You Want To Delete This Field");
        alertDialogBuilder.setTitle("Delete Action");
        alertDialogBuilder.setPositiveButton("Delete", (arg0, arg1) -> {

            deleteMedicine(id);

        });
        alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void deleteMedicine(int id){

        StringRequest request = new StringRequest(Request.Method.POST, URLS.URL_DELETE_MEDICINE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("error")){
                        Toast.makeText(mcontex.getApplicationContext(), "response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mcontex.getApplicationContext(), "Response: "+object.getString("message"), Toast.LENGTH_SHORT).show();

                        if (mcontex instanceof MedicinesActivity){
                            ((MedicinesActivity)mcontex).mExampleList.clear();
                            ((MedicinesActivity)mcontex).mExampleAdapter.notifyDataSetChanged();
                            ((MedicinesActivity)mcontex).getMedicines();
                        }

                    }
                }catch (Exception exception){
                    Toast.makeText(mcontex.getApplicationContext(), "Exception: " + exception, Toast.LENGTH_LONG).show();
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
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        VolleySingleton.getInstance(mcontex.getApplicationContext()).addToRequestQueue(request);

    }

}
