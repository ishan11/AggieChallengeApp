package com.example.christina.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class page1 extends AppCompatActivity {

    public ImageButton imagebutton1;

    public void init(){
        imagebutton1= (ImageButton)findViewById(R.id.imagebutton_contacts);

        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(page1.this, second.class);
                startActivity(intent);
            }
        });
    }
    public ImageButton imagebutton2;
    public void unit(){
        imagebutton2= (ImageButton)findViewById(R.id.imagebutton_status);
        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(page1.this, third.class);
                startActivity(intent1);
            }
        });
        }
    public ImageButton imagebutton3;
    public void fnit(){
        imagebutton3=(ImageButton)findViewById(R.id.imageButton_eeg);
        imagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(page1.this, fourth.class);
                startActivity(intent2);
            }
        });
    }
    public ImageButton imagebutton4;
    public void gnit(){
        imagebutton4=(ImageButton)findViewById(R.id.imageButton_log);
        imagebutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(page1.this, fifth.class);
                startActivity(intent3);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        init();
        unit();
        fnit();
        gnit();
    }
}
