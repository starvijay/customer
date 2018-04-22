package com.example.satmunia.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by SatMunia on 19-04-2018.
 */

public class SingleItemView extends Activity {
    // Declare Variables
    TextView txtname;
    TextView txtemail;
    TextView txtmobile;
    String name;
    String email;
    String mobile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_item);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of rank
        name = i.getStringExtra("name");
        // Get the results of country
        email = i.getStringExtra("email");
        // Get the results of population
        mobile = i.getStringExtra("mobile");

        // Locate the TextViews in singleitemview.xml
        txtname = (TextView) findViewById(R.id.name);
        txtemail = (TextView) findViewById(R.id.email);
        txtmobile = (TextView) findViewById(R.id.mobile);

        // Load the results into the TextViews
        txtname.setText(name);
        txtemail.setText(email);
        txtmobile.setText(mobile);
    }
}