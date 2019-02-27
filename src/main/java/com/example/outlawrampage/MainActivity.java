package com.example.outlawrampage;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button right;
    private Button left;
    private Button jump;
    private ImageView runner;
    private Handler handler = new Handler();
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView background = (ImageView) findViewById(R.id.background);
        background.setImageResource(R.drawable.west);

        runner = (ImageView) findViewById(R.id.runner);
        runner.setImageResource(R.drawable.female_runner);


        right = (Button) findViewById(R.id.right);
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //citation: https://www.youtube.com/watch?v=06rVQ1kUXc4
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        //citation: https://www.youtube.com/watch?v=UxbJKNjQWD8
                        timer = new Timer(); //need to start a new timer every time you press the button
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        runner.setX(runner.getX() + 5);
                                    }
                                });
                            }
                        }, 1, 50);
                        return true;

                    case MotionEvent.ACTION_UP:
                        timer.cancel(); //end the timer when you release the button
                        return true;
                }

                return false;
            }
        });


        left = (Button) findViewById(R.id.left);
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //citation: https://www.youtube.com/watch?v=06rVQ1kUXc4
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        //citation: https://www.youtube.com/watch?v=UxbJKNjQWD8
                        timer = new Timer(); //need to start a new timer every time you press the button
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        runner.setX(runner.getX() - 5);
                                    }
                                });
                            }
                        }, 1, 50);
                        return true;

                    case MotionEvent.ACTION_UP:
                        timer.cancel(); //end the timer when you release the button
                        return true;
                }

                return false;
            }
        });


        jump = (Button) findViewById(R.id.jump);
        jump.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //citation: https://www.youtube.com/watch?v=06rVQ1kUXc4
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        final long startTime = System.currentTimeMillis();

                        //citation: https://www.youtube.com/watch?v=UxbJKNjQWD8
                        timer = new Timer(); //need to start a new timer every time you press the button
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                float currentY = runner.getY();
                                for (int i = 0; i < 10; i++) {
                                    //citation: https://www.youtube.com/watch?v=PGMrMZLNhUk
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            runner.setY(runner.getY() - 50);
                                        }
                                    }, 50);

                                    if (runner.getY() >= currentY - 20) {
                                        jump.setClickable(false);
                                        break;
                                    }
                                }
                            }
                        }, 1, 50);
                        return true;

                    case MotionEvent.ACTION_UP:
                        timer.cancel(); //end the timer when you release the button
                        return true;
                }

                return false;
            }
        });
    }
}

