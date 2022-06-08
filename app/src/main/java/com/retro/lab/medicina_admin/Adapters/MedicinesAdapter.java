package com.retro.lab.medicina_admin.Adapters;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.retro.lab.medicina_admin.Models.MedicinesModel;
import com.retro.lab.medicina_admin.R;
import com.retro.lab.medicina_admin.UpdateMedicineActivity;

import java.util.ArrayList;


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

        String Name =currentItem.getName();
        String Price = currentItem.getPrice();
        String Pharmacy = currentItem.getPharmacy_name();
        String Address = currentItem.getAddress();
        String Serial = currentItem.getSerial();


        holder.mName.setText(Name);
        holder.mPharmacy.setText(Pharmacy);
        holder.mAddress.setText(Address);
        holder.mPrice.setText("Price: " + Price+" $");


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
            deleteMed = itemView.findViewById(R.id.updateMedicine);

            updateMed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext().getApplicationContext(),
                            UpdateMedicineActivity.class));
                }
            });

            deleteMed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diagNew(v);
                }
            });

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

    public void diagNew(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setMessage("Are You Sure You Want To Delete This Field");
        alertDialogBuilder.setTitle("Delete Action");
        alertDialogBuilder.setPositiveButton("أوافق", (arg0, arg1) -> {

        });
        alertDialogBuilder.setNegativeButton("لا أوافق", (dialog, which) -> {

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
