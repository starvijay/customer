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
    TextView txtrank;
    TextView txtcountry;
    TextView txtproduct;
    String rank;
    String country;
    String product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_item);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of rank
        rank = i.getStringExtra("rank");
        // Get the results of country
        country = i.getStringExtra("country");
        // Get the results of population
        product = i.getStringExtra("product");

        // Locate the TextViews in singleitemview.xml
        txtrank = (TextView) findViewById(R.id.rank);
        txtcountry = (TextView) findViewById(R.id.country);
        txtproduct = (TextView) findViewById(R.id.product);

        // Load the results into the TextViews
        txtrank.setText(rank);
        txtcountry.setText(country);
        txtproduct.setText(product);
    }
}