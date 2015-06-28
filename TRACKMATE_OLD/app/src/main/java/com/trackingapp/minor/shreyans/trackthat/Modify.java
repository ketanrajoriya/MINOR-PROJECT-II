package com.trackingapp.minor.shreyans.trackthat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.*;
import android.widget.EditText;
import android.widget.Toast;


public class Modify extends ActionBarActivity {
    SQLiteDatabase db = null;
    EditText tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        //db = openOrCreateDatabase("GroupMember1",Context.MODE_PRIVATE, null);
        db=openOrCreateDatabase("Groupmember",MODE_PRIVATE, null);
        // db.execSQL("create table if not exists group(mobile varchar, First_name varchar, Last_name varchar, Email_id varchar, Phone_no varchar);");
        // db.execSQL("create table if not exists Members (Uid varchar,Fname varchar,Lname varchar,Mobile_no varchar,Email_id varchar)");
        tv1 = (EditText)findViewById(R.id.editText4);
        tv2 = (EditText)findViewById(R.id.editText5);
        tv3 = (EditText)findViewById(R.id.editText3);
        tv4 = (EditText)findViewById(R.id.editText6);
        Button btn2 = (Button) findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddMember = new Intent(Modify.this,MainActivity.class);
                startActivity(openAddMember);
            }
        });

        Button btn3 = (Button) findViewById(R.id.button);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name=tv1.getText().toString();
                String last_name=tv2.getText().toString();
                String email_id=tv3.getText().toString();
                String phone_no=tv4.getText().toString();
                if(first_name==null||first_name==""||first_name.length()<3||last_name==null||last_name==""||last_name.length()<3)
                {
                    show("Please Enter Correct Name.");
                }
                else if(phone_no==null||phone_no==""||phone_no.length()<10)
                {
                    show("Please Enter Correct mobile number.");
                }
                else if(email_id==null||email_id==""||email_id.length()<10)
                {
                    show("Please Enter Correct Email id.");
                }

                else
                {
                    db.execSQL("UPDATE Members SET Fname='"+first_name+"',Lname='"+last_name+"',Email_id='"+email_id+"',Mobile_no='"+phone_no+"' WHERE Uid='"+"8377088016"+"'");
                    Intent  i=new Intent(Modify.this,MainActivity.class);
                    startActivityForResult(i, 500);
                    db.close();
                    finish();
                }
            }
        });
    }

    private void show(String s) { Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group1, menu);
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
