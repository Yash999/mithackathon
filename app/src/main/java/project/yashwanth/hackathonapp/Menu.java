package project.yashwanth.hackathonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void gotoBrocast(View view) {
        startActivity(new Intent(getApplicationContext(),location.class));
    }

    public void gotoTimeline(View view) {
        startActivity(new Intent(getApplicationContext(),timeline.class));
    }
}
