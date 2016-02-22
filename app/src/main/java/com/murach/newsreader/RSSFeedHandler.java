package com.murach.newsreader;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class RSSFeedHandler extends DefaultHandler {
    private RSSFeed breakfastMenu;

    private RSSItem food;
    
    private boolean feedTitleHasBeenRead = false;
    
    private boolean isName = false;
    private boolean isDescription = false;
    private boolean isPrice = false;
    private boolean isCalories = false;
    
    public RSSFeed getBreakfastMenu() {
        return breakfastMenu;
    }
        
    public void startDocument() throws SAXException {
        breakfastMenu = new RSSFeed();
        food = new RSSItem();
    }
    
    public void startElement(String namespaceURI, String localName, 
            String qName, Attributes atts) throws SAXException {
        
        if (qName.equals("food")) {
            food = new RSSItem();
            return;
        }
        else if (qName.equals("name")) {
            isName = true;
            return;
        }
        else if (qName.equals("description")) {
            isDescription = true;
            return;
        }
        else if (qName.equals("price")) {
            isPrice = true;
            return;
        }
        else if (qName.equals("calories")) {
            isCalories = true;
            return;
        }
    }
    
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (qName.equals("food")) {
            breakfastMenu.addItem(food);
            return;
        }
    }
     
    public void characters(char ch[], int start, int length)
    {
        String s = new String(ch, start, length);
        if (isName) {
            food.setName(s);
            isName = false;
        }
        else if (isPrice) {
            food.setPrice(s);
            isPrice = false;
        }
        else if (isDescription) {
            food.setDescription(s);
            isDescription = false;
        }
        else if (isCalories) {
            food.setCalories(s);
            isCalories = false;
        }        
    }
}