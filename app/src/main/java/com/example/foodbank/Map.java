package com.example.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Map extends AppCompatActivity {
    private Button IpohParade,AeonKlebang,AngsanaMall,WeilHotel,HotelDynasty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        IpohParade = (Button)findViewById(R.id.IpohParade);
        AeonKlebang = (Button)findViewById(R.id.AeonKlebang);
        WeilHotel = (Button)findViewById(R.id.WeilHotel);
        AngsanaMall=(Button)findViewById(R.id.AngsanaMall);
        HotelDynasty=(Button)findViewById(R.id.HotelDynasty);


       IpohParade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/Ipoh+Parade/@4.597172,101.090072,15z/data=!4m5!3m4!1s0x0:0x210c32fd8dff9681!8m2!3d4.597172!4d101.090072"));
            startActivity(intent);
            }
        });
       AeonKlebang.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                       Uri.parse("https://www.google.com/maps/place/Aeon+Mall+Ipoh+Klebang/@4.6773225,101.1208056,17z/data=!3m1!4b1!4m5!3m4!1s0x31ca92f92706e4fb:0xa94d4421ac17c5c5!8m2!3d4.6773225!4d101.1229943"));
               startActivity(intent);
           }
       });

        WeilHotel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                       Uri.parse("https://www.google.com/maps/place/WEIL+Hotel/@4.5946965,101.0878903,17z/data=!3m1!4b1!4m8!3m7!1s0x31caec63a9427c0b:0x45f23931d1c47223!5m2!4m1!1i2!8m2!3d4.5946965!4d101.090079"));
                       startActivity(intent);
           }
       });

       AngsanaMall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                       Uri.parse("https://www.google.com/maps/place/Angsana+Mall+(formerly+Greentown+Mall)/@4.6029193,101.0923462,17z/data=!3m1!4b1!4m5!3m4!1s0x31caecf4c869f18f:0x481ac746779449fa!8m2!3d4.6029193!4d101.0945349"));
                       startActivity(intent);
           }
       });

        HotelDynasty.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                       Uri.parse("https://www.google.com/maps/place/Dynasty+Hotel,+218,+Jln+Sultan+Azlan+Shah,+51200+Kuala+Lumpur,+Federal+Territory+of+Kuala+Lumpur/@3.1722019,101.6896081,17z/data=!3m1!4b1!4m5!3m4!1s0x31cc48163a47b6db:0xdbd24b50a4c27fab!8m2!3d3.1722019!4d101.6917968"));
                       startActivity(intent);
           }
       });
    }
}