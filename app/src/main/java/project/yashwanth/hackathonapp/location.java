package project.yashwanth.hackathonapp;

import android.content.Context;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class location extends AppCompatActivity {

    LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mLocationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }
}
