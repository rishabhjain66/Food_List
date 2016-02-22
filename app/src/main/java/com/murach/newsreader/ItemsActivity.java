package com.murach.newsreader;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ItemsActivity extends Activity 
implements OnItemClickListener {

    private RSSFeed food;
    private FileIO io;
    
    private TextView titleTextView;
    private ListView itemsListView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        
        io = new FileIO(getApplicationContext());
        
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        itemsListView = (ListView) findViewById(R.id.itemsListView);
        
        itemsListView.setOnItemClickListener(this);
        
        new DownloadFeed().execute();
    }
    
    class DownloadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            io.downloadFile();
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result) {
            Log.d("News reader", "Feed downloaded");
            new ReadFeed().execute();
        }
    }
    
    class ReadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            food = io.readFile();
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result) {
            Log.d("News reader", "Feed read");
            
            // update the display for the activity
            ItemsActivity.this.updateDisplay();
        }
    }
    
    public void updateDisplay()
    {
        if (food == null) {
            titleTextView.setText("Unable to get RSS food");
            return;
        }

        // set the title for the food
        titleTextView.setText("food benefits");
        
        // get the items for the food
        ArrayList<RSSItem> items = food.getAllItems();

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data = 
                new ArrayList<HashMap<String, String>>();
        for (RSSItem item : items) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", item.getName());
            //map.put("calories", item.getCalories());
            data.add(map);
        }
        
        // create the resource, from, and to variables 
        int resource = R.layout.listview_item;
        String[] from = {"colories", "name"};
        int[] to = {R.id.caloriesTextView, R.id.titleTextView};

        // create and set the adapter
        SimpleAdapter adapter = 
            new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);
        
        Log.d("News reader", "Feed displayed");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, 
            int position, long id) {

        // get the item at the specified position
        RSSItem item = food.getItem(position);

        // create an intent
        Intent intent = new Intent(this, ItemActivity.class);

        intent.putExtra("colories", item.getCalories());
        intent.putExtra("name", item.getName());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("price", item.getPrice());

        this.startActivity(intent);
    }
}