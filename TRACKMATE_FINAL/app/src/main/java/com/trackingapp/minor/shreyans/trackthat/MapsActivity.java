package com.trackingapp.minor.shreyans.trackthat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    final Context context = this;
    Button search,send,view,sat,ter,hyb;
    SQLiteDatabase db=null;
    Cursor cur=null;
    String numList,name;
    Location loc;
    int min=1,max=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );

        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!statusOfGPS)
        {
            AlertDialog.Builder builder  = new AlertDialog.Builder(context);
            builder.setTitle("Your GPS seems to be Off");
            builder.setMessage("Would You Like to Enable it?")
                    .setCancelable(true)
                    .setPositiveButton("Take Me There", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            context.startActivity(myIntent);
                        }
                    })
                    .setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }

        search = (Button) findViewById(R.id.find);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText search  = new EditText(getApplicationContext());
                search.setTextColor(Color.BLUE);
                AlertDialog.Builder find = new AlertDialog.Builder(context);
                find.setTitle("Search");
                find.setMessage("Enter The location You Want to Search");
                find.setView(search);
                find.setPositiveButton("Search",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String loc = search.getText().toString();
                        String url = "http://maps.google.com/?q="+loc;
                        Intent i  = new Intent(Intent.ACTION_VIEW);
                        //i.setClassName("com.trackingapp.minor.shreyans.trackthat","com.trackingapp.minor.shreyans.trackthat.MapsActivity");
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                find.create().show();
            }
        });

        final SmsManager smsManager = SmsManager.getDefault();
        //final String phone = "9990344892";
        final LocationManager locmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        send = (Button) findViewById(R.id.sendloc);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    db = openOrCreateDatabase("Groupmember", MODE_PRIVATE, null);
                    cur = db.rawQuery("Select Email_id,Fname from Members", null);

                    //Num = cur.getCount();
                    cur.moveToFirst();
                    loc = locmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    while (!cur.isAfterLast()) {

                        numList= cur.getString(cur.getColumnIndex("Email_id"));
                        name = cur.getString(cur.getColumnIndex("Fname"));
                        show("got " + numList);


                        smsManager.sendTextMessage( numList, null, "Hello!! My Current Location is: "+"28.519269"+","+"77.364961", null, null);
                        show("SMS Sent to "+name);
                        cur.moveToNext();

                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    show("SMS Not Sent, Please Try Again!!");
                }

            }


        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    public double sendLat(double location)
    {
        return location;
    }

    public double sendLng(double location)
    {
        return location;
    }

    public void show(String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */


    private void setUpMap() {

        LatLng clg = new LatLng(28.519269, 77.364961);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                show("Your Current Location is "+location.getLatitude()+","+location.getLongitude());
                mMap.addMarker(new MarkerOptions().title("Your Current Position").position(new LatLng(location.getLatitude(), location.getLongitude())));
                sendLat(location.getLatitude());
                sendLng(location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider){

            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        try {
            loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng current =  new LatLng(loc.getLatitude(),loc.getLongitude());
            show("Your Current Location is "+loc.getLatitude()+","+loc.getLongitude());
            mMap.addMarker(new MarkerOptions().position(current).title("Your Last Known Location").draggable(true));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        sat = (Button) findViewById(R.id.sat);
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        hyb = (Button) findViewById(R.id.hyb);
        hyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        ter = (Button) findViewById(R.id.ter);
        ter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.519269, 77.364961)).title("You Are Here"));
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clg, 15));
        mMap.setTrafficEnabled(true);

        //mMap.addPolygon(new PolygonOptions().add(new LatLng(30,67)));
    }
}
