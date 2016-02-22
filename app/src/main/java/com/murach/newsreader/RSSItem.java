package com.murach.newsreader;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class RSSItem {
    
    private String name = null;
    private String description = null;
    private String price = null;
    private String calories = null;
    
    public void setName(String title)     {
        this.name = title;
    }
    
    public String getName() {
        return name;
    }
    
    public void setDescription(String description)     {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setPrice(String link) {
        this.price = link;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setCalories(String pubDate) {
        this.calories = pubDate;
    }
    
    public String getCalories() {
        return calories;
    }
    


}