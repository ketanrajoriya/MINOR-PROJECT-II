package com.trackingapp.minor.shreyans.trackthat;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class FirstpageActivity extends ActionBarActivity {

    TextView logo;

    @Override
    protected void onCreate(Bundle shreyans) {
        super.onCreate(shreyans);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        logo = (TextView) findViewById(R.id.textView);
        int notificationId = 101;
        setContentView(R.layout.activity_firstpage);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSound(alarmSound);
        mBuilder.setSmallIcon(R.drawable.snow);
        mBuilder.setContentTitle("Security Started");
        mBuilder.setContentText("Slide The Notification to Dismiss");
        NotificationManager man = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        man.notify(notificationId,mBuilder.build());

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    /*
                    cur = db.rawQuery("select * from login",null);
                    cur.moveToFirst();
                    String first = cur.getString(cur.getColumnIndex("fname"));
                    String last = cur.getString(cur.getColumnIndex("lname"));
                    if(cur.getCount()>0){
                        Intent acti = new Intent(FirstpageActivity.this,welcome.class);
                        startActivity(acti);
                        show("Welcome! You are logged in as "+first+" "+last);
                    }*/
                    //else {
                        Intent act = new Intent("com.trackingapp.minor.shreyans.trackthat.TESTACTIVITY");
                        startActivity(act);
                    //}
                }
            }
        };
        thread.start();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_firstpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}