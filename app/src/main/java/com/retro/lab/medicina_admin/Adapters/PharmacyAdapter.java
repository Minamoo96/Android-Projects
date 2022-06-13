package com.retro.lab.medicina_admin.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import com.retro.lab.medicina_admin.Models.PharmacyModel;
import com.retro.lab.medicina_admin.Models.UserModel;
import com.retro.lab.medicina_admin.PharmaciesActivity;
import com.retro.lab.medicina_admin.R;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;
import com.retro.lab.medicina_admin.UpdateMedicineActivity;
import com.retro.lab.medicina_admin.UpdatePharmacyActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.ExampleviewHolder> {
    private Context mcontex;
    private ArrayList<PharmacyModel> mExamplelist;

    ///lisner for on click
    private  OnItemClickListener mListener;

    public interface  OnItemClickListener{

        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public PharmacyAdapter(Context contex , ArrayList<PharmacyModel> examplelist){
        mcontex = contex;
        mExamplelist = examplelist;

    }

    @NonNull
    @Override
    public ExampleviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontex).inflate(R.layout.pharmacy_card,parent,false);
        return  new ExampleviewHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExampleviewHolder holder, int position) {

        PharmacyModel currentItem = mExamplelist.get(position);

        int id = currentItem.getId();
        String Name = currentItem.getPharmace_name();
        String Address = currentItem.getAddress();
        String number = currentItem.getNumber();
        String Serial = currentItem.getSerial_number();


        holder.mPName.setText(Name);
        holder.mPAddress.setText(Address);
        holder.mPSerial.setText(Serial);

        holder.updatePhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext().getApplicationContext(), UpdatePharmacyActivity.class);
                intent.putExtra("ph_id", id);
                intent.putExtra("Ph_Name", Name);
                intent.putExtra("phone_number", number);
                intent.putExtra("Ph_Address", Address);
                intent.putExtra("Ph_Serial", Serial);
                v.getContext().startActivity(intent);

            }
        });

        holder.deletePhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagNew(v,id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }

    public class ExampleviewHolder extends  RecyclerView.ViewHolder{

        public TextView mPName, mPAddress, mPSerial;
        public AppCompatButton updatePhar, deletePhar;

        public ExampleviewHolder(@NonNull View itemView) {
            super(itemView);
            mPName = itemView.findViewById(R.id.mPharmacyName);
            mPAddress = itemView.findViewById(R.id.mPharmacyAddress);
            mPSerial = itemView.findViewById(R.id.mPharmacySerial);

            updatePhar = itemView.findViewById(R.id.updatePharmacy);
            deletePhar = itemView.findViewById(R.id.deletePharmacy);


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

            deletePharmacy(id);

        });
        alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void deletePharmacy(int id){

        StringRequest request = new StringRequest(Request.Method.POST, URLS.URL_DELETE_PHARMACY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("error")){
                        Toast.makeText(mcontex.getApplicationContext(), "response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mcontex.getApplicationContext(), "Response: "+object.getString("message"), Toast.LENGTH_SHORT).show();

                            if (mcontex instanceof  PharmaciesActivity){
                                ((PharmaciesActivity)mcontex).mExampleList.clear();
                                ((PharmaciesActivity)mcontex).mExampleAdapter.notifyDataSetChanged();
                                ((PharmaciesActivity)mcontex).getPharmacies();
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