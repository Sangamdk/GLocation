package com.example.glocationmain1;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    TextView tv;
    LocationManager locationManager;
    String lattitude,longitude;
    Button cvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        cvid=findViewById(R.id.cvid);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(MainActivity.this, covid19.class));
            }
        });

        ////////////////////ASIGEN CITY//////////////////
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
        /////////////END CITY////////////////////////////////
    }
    ////////////////////////////LOCATION/////////////////////////////

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();

                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                try {
                    Geocoder geocoder=new Geocoder(this);
                    List<Address> addresses=null;
                    addresses = geocoder.getFromLocation(latti,longi,1);
                    String city=addresses.get(0).getLocality();
                    tv.setText(" Current Location :"+city);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                // textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude + "\n" + "Longitude = " + longitude);

            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                try {
                    Geocoder geocoder=new Geocoder(this);
                    List<Address>addresses=null;
                    addresses = geocoder.getFromLocation(latti,longi,1);
                    String city=addresses.get(0).getLocality();
                    tv.setText(" Current Location :"+city);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //       textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude
                //     + "\n" + "Longitude = " + longitude);


            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                try {
                    Geocoder geocoder=new Geocoder(this);
                    List<Address>addresses=null;
                    addresses = geocoder.getFromLocation(latti,longi,1);
                    String city=addresses.get(0).getLocality();
                    tv.setText(" Current Location :"+city);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //   textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude
                //           + "\n" + "Longitude = " + longitude);

            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private  void loc_func(Location location)
    {

        Geocoder geocoder=new Geocoder(this);
        List<Address>addresses=null;
        //  addresses = geocoder.getFromLocation(latti,longi,1);
    }

    //////////////////////////END LOCATION///////////////////////////////

}