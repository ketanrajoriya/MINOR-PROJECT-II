package com.trackingapp.minor.shreyans.trackthat;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    SQLiteDatabase db;
    Button btn2,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddMember = new Intent(MainActivity.this,Group1.class);
                startActivity(openAddMember);
            }
        });
        db=openOrCreateDatabase("Groupmember", MODE_PRIVATE, null);
        db.execSQL("create table if not exists Members (Uid varchar,Fname varchar,Lname varchar,Mobile_no varchar,Email_id varchar)");


        btn2 = (Button) findViewById(R.id.button3);
        btn2.setOnClickListener(this);

        btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(this);

        btn5 = (Button) findViewById(R.id.button5);
        btn5.setOnClickListener(this);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddMember = new Intent(MainActivity.this,Modify.class);
                startActivity(openAddMember);
            }
        });
    }
    public void onClick(View view){
        if(view == btn2){
            Cursor cr= db.rawQuery("select * from Members where Uid='"+"8377088016"+"'",null);

            // cr.moveToFirst();
            if(cr.getCount()==0){
                showMessage("error","norecordfound");
            }
            StringBuffer buffer = new StringBuffer();
            while(cr.moveToNext()){
                buffer.append("First_name   "+cr.getString(1)+"\n");
                buffer.append("Last_name    "+cr.getString(2)+"\n");
                buffer.append("Email_id       "+cr.getString(3)+"\n");
                buffer.append("Phone           "+cr.getString(4)+"\n");
            }
            showMessage("Members Details",buffer.toString());

        }
        if(view ==btn4){
            Intent openAddMember = new Intent(MainActivity.this,Delete.class);
            startActivity(openAddMember);
        }

    }


    public void showMessage(String title,String message){
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
