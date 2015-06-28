package com.trackingapp.minor.shreyans.trackthat;

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

public class Delete extends ActionBarActivity {
    SQLiteDatabase db;
    EditText tv1;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        db=openOrCreateDatabase("Groupmember",MODE_PRIVATE, null);
        tv1 =(EditText)findViewById(R.id.editText);
        btn1= (Button)findViewById(R.id.button6);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_no=tv1.getText().toString();
                if(phone_no==null||phone_no==""||phone_no.length()<10)
                {
                    show("Please Enter Correct mobile number.");
                }

                else
                {
                    Cursor c=db.rawQuery("SELECT * FROM Members WHERE Mobile_no='"+phone_no+"'", null);
                    if(c.moveToFirst())
                    {
                        db.execSQL("DELETE FROM Members WHERE Mobile_no='"+phone_no+"'");
                        show("Record Deleted");
                    }
                }
            }
        });
    }
    private void show(String s){Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
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
