package com.trackingapp.minor.shreyans.trackthat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SMSChat extends ActionBarActivity {

    Button send;
    SQLiteDatabase db=null;
    Cursor cur=null;
    String numList,name;
    EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smschat);
        send = (Button) findViewById(R.id.send);
        msg = (EditText) findViewById(R.id.msg);
        final SmsManager smsManager = SmsManager.getDefault();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = msg.getText().toString();
                    db = openOrCreateDatabase("Groupmember", MODE_PRIVATE, null);
                    cur = db.rawQuery("Select Email_id,Fname from Members", null);

                    //Num = cur.getCount();
                    cur.moveToFirst();
                    //loc = locmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    while (!cur.isAfterLast()) {

                        numList= cur.getString(cur.getColumnIndex("Email_id"));
                        name = cur.getString(cur.getColumnIndex("Fname"));
                        //show("got " + numList);


                        smsManager.sendTextMessage( numList, null,text, null, null);
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
    public void show(String str)
    {

        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smschat, menu);
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
