package com.example.sleepyz.burgar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                if (id == 0){
                    Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(WhitelistActivity.this, PlaceholderActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}
