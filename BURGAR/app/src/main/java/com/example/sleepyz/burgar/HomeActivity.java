package com.example.sleepyz.burgar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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





}
