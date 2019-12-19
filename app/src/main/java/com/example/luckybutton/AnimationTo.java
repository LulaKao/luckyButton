package com.example.luckybutton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationTo extends AppCompatActivity {
    ConstraintLayout CL;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Animation fadein= AnimationUtils.loadAnimation(AnimationTo.this,R.anim.fade_in);
        Animation fadeout= AnimationUtils.loadAnimation(AnimationTo.this,R.anim.fade_out);
        //CL.setAnimation(fadein);
        logo=findViewById((R.id.logo));
        logo.setAnimation(fadein);
        //logo.setAnimation(fadeout);
        Thread timer= new Thread()

        {
            @Override
            public void run() {
                super.run();

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally
                {   overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_out);
                    //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_in);
                    startActivity(new Intent(AnimationTo.this,HomePage.class));
                    finish();

                }


            }
        };
        timer.start();
    }
}