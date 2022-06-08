package com.retro.lab.medicina_admin.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retro.lab.medicina_admin.Models.MedicinesModel;
import com.retro.lab.medicina_admin.Models.UserModel;
import com.retro.lab.medicina_admin.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ExampleviewHolder> {
    private Context mcontex;
    private ArrayList<UserModel> mExamplelist;

    ///lisner for on click
    private  OnItemClickListener mListener;

    public interface  OnItemClickListener{

        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public UserAdapter(Context contex , ArrayList<UserModel> examplelist){
        mcontex=contex;
        mExamplelist = examplelist;

    }

    @NonNull
    @Override
    public ExampleviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontex).inflate(R.layout.user_card,parent,false);
        return  new ExampleviewHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExampleviewHolder holder, int position) {

        UserModel currentItem = mExamplelist.get(position);

        String Name =currentItem.getUsername();
        String Email = currentItem.getEmail();
        String Phone = currentItem.getPhone();


        holder.mUsername.setText(Name);
        holder.mEmail.setText(Email);
        holder.mPhone.setText(Phone);


    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }

    public class ExampleviewHolder extends  RecyclerView.ViewHolder{

        public TextView mUsername, mEmail, mPhone;

        public ExampleviewHolder(@NonNull View itemView) {
            super(itemView);
            mUsername = itemView.findViewById(R.id.mUsername);
            mEmail = itemView.findViewById(R.id.mEmail);
            mPhone = itemView.findViewById(R.id.mPhone);



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
    }}