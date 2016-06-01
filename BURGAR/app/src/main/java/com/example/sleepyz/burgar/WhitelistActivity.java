package com.example.sleepyz.burgar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WhitelistActivity extends AppCompatActivity {

    private ArrayList<String> WhitelistArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whitelist);

        WhitelistArray = new ArrayList<>();

        WhitelistArray.add("Jaap");
        WhitelistArray.add("Henk");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, WhitelistArray);

        ListView lv = (ListView)findViewById(R.id.WhitelistListView);
        lv.setAdapter(adapter);

    }
}
