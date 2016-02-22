package com.murach.newsreader;

import java.util.ArrayList;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class RSSFeed {
    private String name = null;
    private String calories =null;
    private ArrayList<RSSItem> items;

        
    public RSSFeed() {
        items = new ArrayList<RSSItem>(); 
    }

    public void setName(String title) {
        this.name = title;
    }
    public String getName() {
        return name;
    }


    public void setCalories(String calories) {

        this.name = calories;
    }
    public String getCalories() {
        return calories;
    }



    public int addItem(RSSItem item) {
        items.add(item);
        return items.size();
    }
    
    public RSSItem getItem(int index) {
        return items.get(index);
    }
    
    public ArrayList<RSSItem> getAllItems() {
        return items;
    }    
}