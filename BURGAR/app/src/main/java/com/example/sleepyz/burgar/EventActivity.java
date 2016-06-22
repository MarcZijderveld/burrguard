package com.example.sleepyz.burgar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sleepyz.burgar.app.AppConfig;
import com.example.sleepyz.burgar.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventActivity extends AppCompatActivity
{
    private ArrayList<String> events;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        events = new ArrayList<String>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, events);

        ListView lv = (ListView)findViewById(R.id.eventListView);
        lv.setAdapter(adapter);

        checkEvents();
    }

    private void checkEvents() {
        // Tag used to cancel the request
        String tag_string_req = "req_events";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_EVENTS, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("", "Event Response: " + response.toString());

                try {
                    JSONArray jArray = new JSONArray(response);

                    for (int i = 0; i < jArray.length(); i++) {  // line 2
                        JSONObject childJSONObject = jArray.getJSONObject(i);
                        String timestamp = childJSONObject.getString("timestamp");
                        String id     = childJSONObject.getString("alarm_id");
                        events.add(id + ": "  + timestamp);

                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("" , "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();

                AppConfig.preferenceSettings = getSharedPreferences(AppConfig.PREFERENCE_NAME, AppConfig.PREFERENCE_MODE_PRIVATE);
                final String allowedCrownStone = AppConfig.preferenceSettings.getString("crownstone", "default");

                params.put("alarm_id", allowedCrownStone);

                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
