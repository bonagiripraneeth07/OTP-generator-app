package com.example.generatescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
EditText phonenum;
TextView textviewdisplay;
PinView pinview;
Button verify,generatebtn,regenerate;
ProgressBar progressbar;
    String temp;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phonenum= findViewById(R.id.phonenum);
        textviewdisplay= findViewById(R.id.textviewdisplay);
        pinview = findViewById(R.id.pinview);
        verify=findViewById(R.id.verify);
        generatebtn=findViewById(R.id.generatebtn);
        regenerate=findViewById(R.id.regenerate);
progressbar=findViewById(R.id.progressbar);
        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phnum= phonenum.getText().toString();

                if (phnum.isEmpty()){

                }else{
                    Random random = new Random();
                    int val = random.nextInt(4500-1000)+2500;
                    temp = String.valueOf(val);
                    textviewdisplay.setText("Enter the Verification Code We Just sent to Your Number"+phnum);
                    progressbar.setVisibility(View.VISIBLE);
                    boolean handler =new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            verify.setVisibility(View.VISIBLE);
                            pinview.setVisibility(View.VISIBLE);
                            progressbar.setVisibility(View.GONE);
                        }
                    },2000);
                }
                String message ="Your ONE TIME PASSWORD(OTP) is   "+ temp;
               try {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(phnum,null,message,null,null);
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter your Ph:number",Toast.LENGTH_LONG).show();
                }


            }
        });
verify.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String input= pinview.getText().toString();
        if (temp.equals(input)){
            generatebtn.setVisibility(View.GONE);
        boolean handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(in);
                progressbar.setVisibility(View.GONE);
            }
        },2000);
         progressbar.setVisibility(View.VISIBLE);
        }else {
            Toast.makeText(MainActivity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
regenerate.setVisibility(View.VISIBLE);
        }
    }
});
regenerate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        generate();
        generatebtn.setVisibility(View.GONE);
    }
});
    }
    public void generate(){
        String phnum= phonenum.getText().toString();

        if (phnum.isEmpty()){

        }else{
            Random random = new Random();
            int val = random.nextInt(4500-1000)+2500;
            temp = String.valueOf(val);
            textviewdisplay.setText("Enter the Verification Code We Just sent to Your Number"+phnum);
            progressbar.setVisibility(View.VISIBLE);
            boolean handler =new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    verify.setVisibility(View.VISIBLE);
                    pinview.setVisibility(View.VISIBLE);
                    progressbar.setVisibility(View.GONE);
                }
            },2000);
        }
        String message ="Your ONE TIME PASSWORD(OTP) is   "+ temp;
        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phnum,null,message,null,null);
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please Enter your Ph:number",Toast.LENGTH_LONG).show();
        }



    }
}