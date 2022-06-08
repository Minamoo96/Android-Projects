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

import com.retro.lab.medicina_admin.Models.PharmacyModel;
import com.retro.lab.medicina_admin.Models.UserModel;
import com.retro.lab.medicina_admin.R;
import com.retro.lab.medicina_admin.UpdateMedicineActivity;
import com.retro.lab.medicina_admin.UpdatePharmacyActivity;

import java.util.ArrayList;

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
        mcontex=contex;
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

        String Name =currentItem.getPharmace_name();
        String Address = currentItem.getAddress();
        String Serial = currentItem.getSerial_number();


        holder.mPName.setText(Name);
        holder.mPAddress.setText(Address);
        holder.mPSerial.setText(Serial);

        holder.updatePhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext().getApplicationContext(), UpdatePharmacyActivity.class);
                intent.putExtra("Ph_Name", Name);
                intent.putExtra("Ph_Address", Address);
                intent.putExtra("Ph_Serial", Serial);
                v.getContext().startActivity(intent);
            }
        });

        holder.deletePhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagNew(v);
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

    public void diagNew(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setMessage("Are You Sure You Want To Delete This Field");
        alertDialogBuilder.setTitle("Delete Action");
        alertDialogBuilder.setPositiveButton("Delete", (arg0, arg1) -> {

        });
        alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}