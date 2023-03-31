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
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FAdapter extends RecyclerView.Adapter<FAdapter.MyViewHolder> {
    Context context;
    ArrayList<FoodItem> list;
    RecyclerView recyclerView;
    //DatabaseReference ref;




    public FAdapter(Context context, ArrayList<FoodItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        FoodItem user = list.get(position);
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

                Intent i = new Intent(context, ClaimedDisplay.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //text to display
                i.putExtra("FoodName",text1);
                i.putExtra("Quantity",text2);
                i.putExtra("locations",text3);
                i.putExtra("Description",text4);
                i.putExtra("Date",text5);

                context.startActivity(i);


            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.FoodName.getContext());
                builder.setTitle("CONFIRM CLAIM FOOD");
                builder.setMessage("DOUBLE CONFIRM?");

                builder.setPositiveButton("CLAIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("FoodItem");
                       ref.child(user.getFoodName()).setValue(null);

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

        return list.size();
    }
    /*void setFilter (ArrayList<FoodItem> filterModel){
        FoodItem = new ArrayList<>();
        FoodItem.add
    }*/


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        Button deleteBtn,report;
        TextView FoodName,Description,Quantity, locations,Date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        FoodName=itemView.findViewById(R.id.foodname1);
        Description=itemView.findViewById(R.id.desc1);
        Quantity=itemView.findViewById(R.id.amount1);
        locations=itemView.findViewById(R.id.location1);
        Date=itemView.findViewById(R.id.datetime);


            deleteBtn = (Button)itemView.findViewById(R.id.claim);
            //report = (Button)itemView.findViewById(R.id.report);

        }
    }


}


