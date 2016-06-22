package com.example.sleepyz.burgar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sleepyz.burgar.app.AppConfig;
import com.example.sleepyz.burgar.app.AppController;
import com.example.sleepyz.burgar.helper.SQLiteHandler;
import com.example.sleepyz.burgar.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WhitelistActivity extends AppCompatActivity {

    private ArrayList<String> FriendlistArray;
    private ArrayAdapter adapter;

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private SQLiteHandler db;
    private String friendlistSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        db = new SQLiteHandler(getApplicationContext());
        friendlistSelect = "http://project.cmi.hr.nl/2015_2016/emedia_mt2b_t3/burguard_api/get_trustees.php";

        FriendlistArray = new ArrayList<>();
        Button button = (Button)findViewById(R.id.addFriendButton);
        final EditText editText = (EditText)findViewById(R.id.addFriend);

        getUsers();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, FriendlistArray);

        ListView lv = (ListView)findViewById(R.id.WhitelistListView);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                createDialog(position);
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

    public void getUsers() {

        Log.d("Ik begin uberhaupt", "Ja, zo spel je dat");

        String tag_string_req = "req_getUsers";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                friendlistSelect, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("blablabla", "blablabla");

                Log.d("", "Event Response: " + response.toString());

                try {
                    JSONArray jArray = new JSONArray(response);

                    for (int i = 0; i < jArray.length(); i++) {  // line 2
                        JSONObject childJSONObject = jArray.getJSONObject(i);
                        String email = childJSONObject.getString("burguard_users_id");
                        FriendlistArray.add(email);

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
                params.put("alarm_id", "ED:E3:27:E4:EB:68");

                return params;
            }
        };



        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public Dialog createDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(WhitelistActivity.this);
        builder.setMessage(R.string.delete_dialog)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FriendlistArray.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        builder.create();
        return builder.show();

    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
