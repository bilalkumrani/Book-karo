package com.example.barberbookingapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class directions extends AppCompatActivity {

    EditText yourlocation;
    Button btn_direction;
    String destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        yourlocation = (EditText)findViewById(R.id.yourlocation);
        btn_direction = (Button)findViewById(R.id.btn_direction);

        destination = getIntent().getStringExtra("ServiceProviderLocation");

        Log.e("We going to fly",destination);

        btn_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source = yourlocation.getText().toString().trim();
                String destin = destination;
                if(source.equals("") && destin.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter your location", Toast.LENGTH_SHORT).show();

                }else{
                    DisplayTrack(source,destin);
                }

            }

            private void DisplayTrack(String source, String destin) {

                try{

                    // When maps is installed on your device
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+destin);

                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    // When maps is not installed on your device it will help by the help of playstore
                    Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }



            }
        });





    }


}