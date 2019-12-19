package com.example.luckybutton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static int counter = 3;
    Float angle=0.0f;
    public static int speed=5;
    int result;
    boolean play=false;
    ImageView roulette;
    TextView msg;
    Button btn,buy;
    ImageButton btnIntent;
    TextView head;
    static TextView speedSet;
    SeekBar SB;
    FrameLayout FL;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg=findViewById(R.id.msg);
        btn=findViewById(R.id.button);
        btnIntent=findViewById(R.id.button2);
        buy=findViewById(R.id.moneyForKevin);
        SB=findViewById(R.id.seekBar);
        head=findViewById(R.id.headText);
        speedSet=findViewById(R.id.setSpeed);
        FL=findViewById(R.id.frameLayout);
        setHead();


        roulette=findViewById(R.id.roulette);
        //增加抽獎次數(測試版免課金)
        SB.setProgress(speed-1);
        SB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
                                      {
                                          @Override
                                          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                              speed=progress+1;
                                              speedSet.setText(String.valueOf(speed));
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


        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, shakeshake.class);
                startActivity(intent);
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
                     //angle=0.0f;
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
                    result=(int)(angle%360);
                    Log.d("AngleResult",String.valueOf(angle));
                    Log.d("result",String.valueOf(result));
                    if(angle>0&&angle<=60)
                    {msg.setText("你抽中"+"Kevin跳大腿舞"+"\n這沙小.." );}

                    else if(angle>60&&angle<=180)
                    {msg.setText("你抽中"+"Kevin請大家吃飯"+"\nㄟ..越來越過分耶" );}

                    else if(angle>180&&angle<=220)
                    {msg.setText("你抽中"+"頭獎!!!"+"\n恭喜啦");}

                    else if(angle>220&&angle<=240)
                    {
                        msg.setText("你抽中"+"驚喜獎"+"\n"+"Surprise");
                        btn.setVisibility(View.INVISIBLE);
                        FL.setVisibility(View.INVISIBLE);

                        Thread thread2=new Thread(new Runnable() {
                            @Override
                            public void run() {

                                {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                btnIntent.setVisibility(View.VISIBLE);
                                                /*Intent i=new Intent(MainActivity.this,shakeshake.class);
                                                Bundle bag=new Bundle();
                                                bag.putInt("speed",speed);
                                                bag.putInt("count",counter);*/

                                            }
                                        });
                                        run();
                                    }
                                }
                                //Thread.interrupted();

                            }
                        });
                        thread2.start();




                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                finally {
                                    btnIntent.setVisibility(View.VISIBLE);
                                }
                            }
                        });*/
                    }
                    else if(angle>240&&angle<=300)
                    {msg.setText("你抽中"+"Kevin幫忙寫作業!!!"+"\n同學們就是要互相幫忙");}

                    else if(angle>300&&angle<=360)
                    {msg.setText("你抽中"+"Kevin請飲料");}

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
             //Thread.interrupted();

         }
     });
        thread.start();
    }
    //更新提示文字
    public void setHead(){
        head.setText("轉轉樂~命運就在你手中\n剩下次數:"+counter);
    }



}