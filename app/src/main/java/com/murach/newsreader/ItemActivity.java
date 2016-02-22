package com.murach.newsreader;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ItemActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        
        // get references to widgets
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView priceTextView = (TextView) findViewById(R.id.pubDateTextView);
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        TextView caloriesTextView = (TextView) findViewById(R.id.caloriesTextView);
        
        // get the intent
        Intent intent = getIntent();
        
        // get data from the intent
        //String calories = intent.getStringExtra("calories");
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("description").replace('\n', ' ');


        // display data on the widgets
        priceTextView.setText("Price : "+price);
        titleTextView.setText(name);
        //caloriesTextView.setText("Calories : "+calories);
        descriptionTextView.setText(description); 

    }

    @Override
    public void onClick(View v) {
        // get the intent




    }
}