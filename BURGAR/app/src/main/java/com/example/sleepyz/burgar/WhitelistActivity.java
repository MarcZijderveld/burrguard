package com.example.sleepyz.burgar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WhitelistActivity extends AppCompatActivity {

    private ArrayList<String> FriendlistArray;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        FriendlistArray = new ArrayList<>();
        Button button = (Button)findViewById(R.id.addFriendButton);
        final EditText editText = (EditText)findViewById(R.id.addFriend);

        FriendlistArray.add("Jaap");
        FriendlistArray.add("Henk");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, FriendlistArray);

        ListView lv = (ListView)findViewById(R.id.WhitelistListView);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                FriendlistArray.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendlistArray.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

    }

}
