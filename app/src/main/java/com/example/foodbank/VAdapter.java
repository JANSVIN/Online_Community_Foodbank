package com.example.foodbank;

import android.content.Intent;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VAdapter extends RecyclerView.Adapter<VAdapter.MyViewHolder2>{

    Context context2;
    ArrayList<FoodItem> list2;
  

    public VAdapter(Context context, ArrayList<FoodItem> list2) {
        this.context2 = context;
        this.list2 = list2;
    }



    @NonNull
    @Override
    public VAdapter.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context2).inflate(R.layout.volunteeritem,parent,false);
        return new VAdapter.MyViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        FoodItem user = list2.get(position);
        holder.FoodName.setText("FOOD NAME: "+user.getFoodName());
        holder.Quantity.setText("QUANTITY: "+user.getQuantity());
        holder.locations.setText("LOCATIONS: "+user.getLocations());
        holder.Date.setText("DONATED DATE :"+user.getDate());
        holder.Description.setText("DESCRIPTION: "+user.getDescription());

        holder.FoodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = holder.FoodName.getText().toString();
                String text2 = holder.Quantity.getText().toString();
                String text3 = holder.locations.getText().toString();
                String text4 = holder.Description.getText().toString();
                String text5 = holder.Date.getText().toString();

                Intent i = new Intent(context2, Reserved_Display.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //text to display
                i.putExtra("FoodName",text1);
                i.putExtra("Quantity",text2);
                i.putExtra("locations",text3);
                i.putExtra("Description",text4);
                i.putExtra("Date",text5);

                context2.startActivity(i);
            }
        });

    }



    @Override
    public int getItemCount() {
        return list2.size();
    }


    public static class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView FoodName,Description,Quantity, locations,Date;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            FoodName=itemView.findViewById(R.id.foodname2);
            Description=itemView.findViewById(R.id.desc2);
            Quantity=itemView.findViewById(R.id.amount2);
            locations=itemView.findViewById(R.id.location2);
            Date=itemView.findViewById(R.id.datetime1);



        }
    }
}
