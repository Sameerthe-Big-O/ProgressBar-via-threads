package com.example.progressbar_via_threads;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ProgressBar myProgressBar;
    private  int statusProgress=0;
    private Handler myHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //*here cpomes
        myProgressBar=findViewById(R.id.progressbar);

        //*the runnable interface allows us to run the looper
        // or a queue contains the sequence of message
        //*sinc thread natively can't do that,
        //*allows us to do stuff in the backgroud thread
        //*but not blocking the main thread
        new Thread(new Runnable() {
            @Override
            public void run() {
            while (statusProgress<100){
                statusProgress+=1;
                try {
                    // Sleep for 50 milliseconds.
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                };
                //*when we want to communicate with Ui thread while in the background thread we need
                //*we need to use the post method of the Runnable object with it's run method
                myHandler.post(new Runnable() {
                    public void run() {
                        myProgressBar.setProgress(statusProgress);
                    }
                });
            }
            }
        }).start();
    }
}