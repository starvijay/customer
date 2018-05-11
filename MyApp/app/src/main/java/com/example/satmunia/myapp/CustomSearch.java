package com.example.satmunia.myapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class CustomSearch extends AppCompatActivity {

    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;
    ArrayList <String> name = new ArrayList <String>();
    ArrayList <String> email= new ArrayList <String>();
    ArrayList <String> mobile= new ArrayList <String>();
    ArrayList <String> location= new ArrayList <String>();

    ArrayList<SearchCustomer> arraylist = new ArrayList<SearchCustomer>();
    String URL = "https://glacial-crag-90181.herokuapp.com/download";
    JSONArray gg;
    JSONObject obj;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search);

        list = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter( this, arraylist);
        list.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();



        Log.e("search",arraylist.toString());

        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {


                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                Log.e("Search sathish",text);
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jo = new JsonObjectRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            gg= response.getJSONArray("customer");
                            Log.e("sathish",gg.toString());
                            for (int i = 0; i < gg.length(); i++) {
                                obj = gg.getJSONObject(i);
                                name.add(obj.getString("Product"));
                                email.add(obj.getString("email"));
                                mobile.add(obj.getString("Phone"));
                                location.add(obj.getString("Location"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < name.size(); i++) {
                            SearchCustomer wp = new SearchCustomer(name.get(i), email.get(i), mobile.get(i),location.get(i));
                            arraylist.add(wp);
                        }
                        adapter.setAll(arraylist);
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response",error.toString());

                    }
                }
        );
        jo.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jo);





    }
}
