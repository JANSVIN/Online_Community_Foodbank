package com.example.foodbank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class RAdapter extends RecyclerView.Adapter<RAdapter.MyViewHolder1> {

    Context context1;
    ArrayList<FoodItem> list1;
    RecyclerView recyclerView;

    public RAdapter(Context context, ArrayList<FoodItem> list1) {
        this.context1 = context;
        this.list1 = list1;
    }


    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context1).inflate(R.layout.item,parent,false);
        return new MyViewHolder1(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, final int position) {

        FoodItem user = list1.get(position);
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

                Intent i = new Intent(context1, ClaimedDisplay.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //text to display
                i.putExtra("FoodName",text1);
                i.putExtra("Quantity",text2);
                i.putExtra("locations",text3);
                i.putExtra("Description",text4);
                i.putExtra("Date",text5);

                context1.startActivity(i);


            }
        });

       holder.reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.FoodName.getContext());
                builder.setTitle("CONFIRM COMPLETE FOOD");
                builder.setMessage("DOUBLE CONFIRM?");

                builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("FoodReserve");
                        ref.child(user.getFoodName()).setValue(null);
                        Toast.makeText(holder.FoodName.getContext(), "DONE", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.FoodName.getContext(), "CANCEL", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();



            }
        });





    }



    @Override
    public int getItemCount() {

        return list1.size();
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder {
        Button reserveBtn;
        TextView FoodName,Description,Quantity, locations,Date;
        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);

            FoodName=itemView.findViewById(R.id.foodname1);
            Description=itemView.findViewById(R.id.desc1);
            Quantity=itemView.findViewById(R.id.amount1);
            locations=itemView.findViewById(R.id.location1);
            Date=itemView.findViewById(R.id.datetime);

            reserveBtn = (Button)itemView.findViewById(R.id.claim);
        }
    }
}
