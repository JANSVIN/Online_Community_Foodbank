package com.example.foodbank;

import static com.example.foodbank.Constants.TOPIC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodbank.api.ApiUtilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notifications extends AppCompatActivity {
    private EditText title,message;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        button = findViewById(R.id.submit);
        title = findViewById(R.id.title);
        message = findViewById(R.id.detail);


        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titletext = title.getText().toString();
                String msgTxt = message.getText().toString();


                if (!titletext.isEmpty() && !msgTxt.isEmpty()){
                    PushNotification notification = new PushNotification(new NotificationData(titletext,msgTxt),TOPIC);
                    sendNotifications(notification);
                    
                }
            }
        });




    }

    private void sendNotifications(PushNotification notification) {

        ApiUtilities.getClient().sendNotifications(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if(response.isSuccessful())
                    Toast.makeText(Notifications.this, "DONE", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Notifications.this, "ERROR", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {
                Toast.makeText(Notifications.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}