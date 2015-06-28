package com.trackingapp.minor.shreyans.trackthat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class welcome extends ActionBarActivity {

    Button map;
    Button grp;
    TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        msg = (TextView)findViewById(R.id.textView6);
        map = (Button) findViewById(R.id.button2);
        grp = (Button) findViewById(R.id.group);
        String nam = getIntent().getExtras().getString("name");
        //msg.setText(getIntent().getExtras().getString("name"));
        msg.append(" To The App "+nam);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maps = new Intent(welcome.this,HomePage.class);
                startActivity(maps);
               }
        });

        grp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent grpact = new Intent(welcome.this,MainActivity.class);
                startActivity(grpact);
            }
        });

    }

    /*public void maps(View sp)
    {
        Intent Startup = new Intent(welcome.this, MapsActivity.class );
        //toStartup.putExtra("name",name);
        //toStartup.putExtra("pass",pass);
        startActivity(Startup);
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
