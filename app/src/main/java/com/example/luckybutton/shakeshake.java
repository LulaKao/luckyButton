package com.example.luckybutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class shakeshake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakeshake);

        //gif開始
        GifImageView ImageView=findViewById(R.id.test);
        try {
            GifDrawable gifDrawable=new GifDrawable(getResources(),R.drawable.g);

            ImageView.setImageDrawable(gifDrawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //gif結束



            Button nextPageBtn = (Button) findViewById(R.id.back);

            nextPageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(shakeshake.this, MainActivity.class);
                    startActivity(intent);
                }

            });

        }




    }

