package com.trackingapp.minor.shreyans.trackthat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TestActivity extends ActionBarActivity {

    Button enter;
    EditText name,passwd;
    String username,pass;
    View sp;
    SQLiteDatabase db = null;
    Cursor cur=null,curd=null;
    long lastPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        db = openOrCreateDatabase("userdb",MODE_PRIVATE,null);

        enter = (Button) findViewById(R.id.button_enter);
        passwd = (EditText) findViewById(R.id.editText2);
        name = (EditText) findViewById(R.id.editText);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Intent go;
        if(pref.getBoolean("is_first",true))
        {
            editor.putBoolean("is_first",false);
            editor.apply();
            go = new Intent(TestActivity.this,signup.class);
            startActivity(go);
            finish();
        }
      /*  else
        {
            curd = db.rawQuery("select * from login",null);
            curd.moveToFirst();
            String first = curd.getString(curd.getColumnIndex("fname"));
            String last = curd.getString(curd.getColumnIndex("lname"));
            if(curd.getCount()>0) {
                Intent acti = new Intent(TestActivity.this, welcome.class);
                startActivity(acti);
                show("Welcome! You are logged in as " + first + " " + last);
            }
        }*/


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fone = name.getText().toString();
                String pwd = passwd.getText().toString();
                try {
                    cur = db.rawQuery("select * from login where mobile_no ='" + name.getText().toString() + "'and password = '" + passwd.getText().toString() + "'", null);
                    cur.moveToFirst();
                    String username = cur.getString(cur.getColumnIndex("fname"));
                    String pass = cur.getString(cur.getColumnIndex("password"));
               /* if(fone!=username || fone==null)
                {
                    show("Please Enter The Correct Username");
                }
                else if(pwd!=pass || fone==null)
                {
                    show("Your Password is Wrong!!!");
                }*/
                    //disp("username: "+username);

                   Intent start = new Intent(TestActivity.this, Navigator.class).putExtra("name", username);
                   startActivity(start);
                    //disp("hello"+username);
                    db.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    show("Please Enter Correct Details");
                }
            }
        });


    }

    public void disp(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_SHORT);
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastPress > 5000)
        {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_LONG).show();
            lastPress = currentTime;

        }

        else
        {
            super.onBackPressed();
        }
    }

    public void show(String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }

    /*public void tostartup(View sp)
    {
        Intent Startup = new Intent(this, welcome.class ).putExtra("name",name.getText().toString());
        //toStartup.putExtra("name",name);
        //toStartup.putExtra("pass",pass);
        startActivity(Startup);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
