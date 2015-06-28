package com.trackingapp.minor.shreyans.trackthat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class signup extends ActionBarActivity{

    Button create;
    EditText mail;
    EditText pwd;
    EditText firstname;
    EditText lastname;
    EditText phone;
    SQLiteDatabase db;
    String frstname,lstname,fone,email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toast.makeText(this,"Enter Your Details Here",Toast.LENGTH_SHORT).show();
        mail = (EditText) findViewById(R.id.editText3);
        pwd = (EditText) findViewById(R.id.editText7);
        firstname = (EditText) findViewById(R.id.editText4);
        lastname = (EditText) findViewById(R.id.editText5);
        phone = (EditText) findViewById(R.id.editText6);
        create = (Button) findViewById(R.id.coords);
        db = openOrCreateDatabase("userdb", MODE_PRIVATE, null);
        db.execSQL("create table if not exists login (fname varchar,lname varchar,mobile_no varchar,email_id varchar,password varchar)");


       create.setOnClickListener(new View.OnClickListener() {

           @Override

            public void onClick(View v) {
               frstname = firstname.getText().toString();
               lstname = lastname.getText().toString();
               fone = phone.getText().toString();
               pass = pwd.getText().toString();
               email = mail.getText().toString();
               if(mail.getText().toString()==null || mail.getText().toString()== "" || mail.getText().toString().length()<6)
                {
                    show("Enter An EmailId");
                }
                else if(pwd.getText().toString()== null ||  pwd.getText().toString()== "" || pwd.getText().toString().length()<7)
                {
                    show("Please Create A Strong Password");
                }
                else if(firstname.getText().toString()== null ||  firstname.getText().toString()== "" || firstname.getText().toString().length()<2)
                {
                   show("Please Enter Your First Name");
                }
                else if(lastname.getText().toString()== null ||  lastname.getText().toString()== "" || lastname.getText().toString().length()<2)
                {
                   show("Please Enter Your Last Name");
                }
                else if(phone.getText().toString()== null ||  phone.getText().toString()== "" ||  phone.getText().toString().length()<10  )
                {
                   show("Please Enter a Phone Number");
                }
                else
                {
                    db.execSQL("insert into login values('"+frstname+"','"+lstname+"','"+fone+"','"+email+"','"+pass+"')");
                    show("Your Account Has Been Successfully Created");
                    db.close();
                    Intent i = new Intent(signup.this,TestActivity.class);
                    startActivity(i);
                }


            }

        });

    }

    public void show(String str)
    {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
