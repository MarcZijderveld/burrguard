package com.example.sleepyz.burgar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.sleepyz.burgar.R;
import com.example.sleepyz.burgar.app.AppConfig;
import com.example.sleepyz.burgar.app.AppController;
import com.example.sleepyz.burgar.helper.SQLiteHandler;
import com.example.sleepyz.burgar.helper.SessionManager;

public class HistoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }

    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_events";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_EVENTS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("", "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
//                        // user successfully logged in
//                        // Create login session
//                        session.setLogin(true);
//
//                        // Now store the user in SQLite
//                        String uid = jObj.getString("uid");
//
//                        JSONObject user = jObj.getJSONObject("user");
//                        String name = user.getString("name");
//                        String email = user.getString("email");
//                        String created_at = user
//                                .getString("created_at");
//                        String crownstone = user.getString("crownstone_id");
//
//                        AppConfig.preferenceSettings = getSharedPreferences(AppConfig.PREFERENCE_NAME, AppConfig.PREFERENCE_MODE_PRIVATE);
//                        AppConfig.preferenceEditor = AppConfig.preferenceSettings.edit();
//                        AppConfig.preferenceEditor.putString("crownstone", crownstone);
//                        AppConfig.preferenceEditor.commit();
//
//                        Log.d("Crownstone ID: ", crownstone);
//
//                        // Inserting row in users table
////                        if(db.)
////                        db.addUser(name, email, uid, created_at);
//
//                        // Launch main activity
//                        Intent intent = new Intent(HistoryActivity.this,
//                                BluetoothActivity.class);
//                        startActivity(intent);
//                        finish();
//
//                    } else {
//                        // Error in login. Get the error message
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
//                    }
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
                Log.e("", "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
