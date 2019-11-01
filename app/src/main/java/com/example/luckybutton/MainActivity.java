package com.example.luckybutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int counter = 3;
    Float angle=0.0f;
    int speed=5;
    int result;
    boolean play=false;
    ImageView roulette;
    TextView msg;
    Button btn;
    Button buy;
    TextView head;
    TextView speedSet;
    SeekBar SB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg=findViewById(R.id.msg);
        btn=findViewById(R.id.button);
        buy=findViewById(R.id.moneyForKevin);
        SB=findViewById(R.id.seekBar);
        head=findViewById(R.id.headText);
        speedSet=findViewById(R.id.setSpeed);
        setHead();


        roulette=findViewById(R.id.roulette);
        //增加抽獎次數(測試版免課金)
        SB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
                                      {
                                          @Override
                                          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                              speedSet.setText(String.valueOf(progress));
                                              speed=progress;

                                          }

                                          @Override
                                          public void onStartTrackingTouch(SeekBar seekBar) {

                                          }

                                          @Override
                                          public void onStopTrackingTouch(SeekBar seekBar) {



                                          }
                                      });

                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counter = counter + 3;
                        Toast.makeText(MainActivity.this, "你送了一筆錢給Kevin換取3次抽獎機會,\n謝謝老闆", Toast.LENGTH_SHORT).show();
                        setHead();
                    }
                });

        //按下去轉啊轉
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(play==false)
                {if(counter<=0)
                    {msg.setText("抽獎次數已經用光囉");}
                 else
                    {play=true;
                     angle=0.0f;
                     counter--;
                        head.setText("轉轉樂~命運就在你手中\n剩下次數:"+counter);
                     roulette.setRotation(angle);
                        start_thread();
                        btn.setText("停止轉盤");
                    }
                }
                //停止事件
                else {
                    play=false;
                    btn.setText("啟動轉盤");
                    //兌獎內容之後要用陣列存,暫時懶得弄
                    //result=(int)(angle%360);
                    if(angle>22.5&&angle<=67.5)
                    {msg.setText("你抽中"+"Kevin跳大腿舞"+"\n這沙小.." );}

                    else if(angle>67.5&&angle<=112.5)
                    {msg.setText("你抽中"+"頭獎!!!"+"\n恭喜啦" );}

                    else if(angle>112&&angle<=157)
                    {msg.setText("你抽中"+"安慰獎");}


                    else if(angle>157.5&&angle<=202.5)
                    {msg.setText("你抽中"+"我不知道寫甚麼");}

                    else if(angle>202.5&&angle<=247.5)
                    {msg.setText("你抽中"+"Kevin請飲料");}

                    else if(angle>247.5&&angle<=292.5)
                    {msg.setText("你抽中"+"Kevin幫忙寫作業!!!");}

                    else if(angle>292.5&&angle<=337.5)
                    {msg.setText("你抽中"+"Kevin請大家吃飯"+"\nㄟ..越來越過分耶");}


                    else if(angle>337.5 ||angle<=22.5)
                    {msg.setText("你抽中"+"Kevin請吃飯");}


                }
            }
        });


    }
    //設定怎麼個轉法
    public void start_thread(){
     Thread thread=new Thread(new Runnable() {
         @Override
         public void run() {
             while (play == true)
             {
                 try {
                     Thread.sleep(10);
                 } catch (Exception e) {
                     e.printStackTrace();
                 } finally {
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             angle = angle +speed;
                             angle = angle % 360;
                             roulette.setRotation(angle);
                         }
                     });
                     run();
                 }
            }

         }
     });
        thread.start();
    }
    //更新提示文字
    public void setHead(){
        head.setText("轉轉樂~命運就在你手中\n剩下次數:"+counter);
    }
}