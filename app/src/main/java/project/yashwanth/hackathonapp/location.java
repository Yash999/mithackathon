package project.yashwanth.hackathonapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class location extends AppCompatActivity {

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private RadioGroup radioGroup;
    private RadioButton r1,r2,r3;
    Context mContext;
    double lon, lat;
    TextView tvloc;
    String adr;
    Button btn;
    String level="low";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mContext = this;
        tvloc = (TextView) findViewById(R.id.tv_address);
        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rhigh)
                    level="High";
                else if(i==R.id.rmedium)
                    level="Medium";
                else if(i==R.id.rlow)
                    level="Low";
            }
        });
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try {
                    //Toast.makeText(mContext, "Location: " + location.toString(), Toast.LENGTH_LONG).show();
                    lon = location.getLongitude();
                    lat = location.getLatitude();
                    Toast.makeText(mContext, "Got the location " + lon + ", " + lat, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    //Toast.makeText(mContext, "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                //Toast.makeText(mContext, "Status Changed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderEnabled(String s) {
                Toast.makeText(mContext, "GPS Provider Enabled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderDisabled(String s) {
                Toast.makeText(mContext, "GPS Provider Disabled", Toast.LENGTH_LONG).show();
            }
        };

        try {
            if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //ask for permission at runtime
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //updateUiOfLocation(location);

                        lon = location.getLongitude();
                        lat = location.getLatitude();
                        Toast.makeText(mContext, "Got the location " + lon + ", " + lat, Toast.LENGTH_LONG).show();
                        getAddress(lat, lon);
                    }
                });

            } else {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
                mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //updateUiOfLocation(location);

                        lon = location.getLongitude();
                        lat = location.getLatitude();
                        Toast.makeText(mContext, "Got the location " + lon + ", " + lat, Toast.LENGTH_LONG).show();
                        getAddress(lat, lon);
                    }
                });
            }

        } catch (Exception e) {
            Toast.makeText(mContext, "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.v("IGA", "Address" + add);
            Toast.makeText(mContext, "Got the location " + add, Toast.LENGTH_LONG).show();
            tvloc.setText("Location: " + add.toString());
            adr = add.toString();
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void sendBroadcast(View view) {
        try {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("LocationUpdate");
            String postID = databaseReference.push().getKey();
            String time = "" + (DateFormat.getTimeInstance().format(new Date()));
            String date = "" + (DateFormat.getDateInstance().format(new Date()));
            EditText v = (EditText) findViewById(R.id.ed_nov);
            String info[] = {
                    "" + postID,
                    "" + (time + "--" + date),
                    "" + adr,
                    "" + level,
                    "" + Integer.parseInt(v.getText().toString())
            };
            locationTemplate mLocationTemplate = new locationTemplate(info);
            databaseReference.child(postID).setValue(mLocationTemplate);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
