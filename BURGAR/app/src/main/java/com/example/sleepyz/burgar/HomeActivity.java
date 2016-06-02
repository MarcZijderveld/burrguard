package com.example.sleepyz.burgar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.sleepyz.burgar.helper.SessionManager;

public class HomeActivity extends AppCompatActivity {

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new SessionManager(getApplicationContext());
    }

    public void onButtonAlarmClick(View v) {
        ToggleButton mToggle = (ToggleButton)findViewById(R.id.toggleButton);

        if (mToggle.isChecked()) {
            // The toggle is enabled
            Toast t = Toast.makeText(this, "You have turned the alarm on", Toast.LENGTH_SHORT);
            t.show();
        } else {
            // The toggle is disabled
            Toast t = Toast.makeText(this, "You have turned the alarm off", Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void onButtonWhiteListClick(View v)
    {
        Intent intent = new Intent(HomeActivity.this, WhitelistActivity.class);
        startActivity(intent);
        finish();
    }

    //------------------------------Options Menu Stuff ------------------------------
    public void onButtonLogOff()
    {
       if(session.isLoggedIn())
       {
           session.setLogin(false);
           Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
           startActivity(intent);
           finish();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                onButtonLogOff();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //----------------------------------------------------------------------------------




}
