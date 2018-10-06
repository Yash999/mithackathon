package project.yashwanth.hackathonapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class launcher extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        tv=(TextView)findViewById(R.id.ltv);
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/BerkshireSwash_regular.ttf");
        tv.setTypeface(tf);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        },3000);
    }
}
