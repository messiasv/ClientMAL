package com.example.mooncat.clientmal;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class AnimeParserXML {

    public static LinkedList<Anime> parseList(String xml)throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        parser.setInput(is, "UTF-8");
        int compt=0;
        LinkedList<Anime> animeList = new LinkedList<>();
        int eventType = parser.getEventType();
        while (eventType != parser.END_DOCUMENT) {
            if (eventType == parser.START_DOCUMENT) {
            } else if (eventType == parser.END_DOCUMENT) {
            } else if (eventType == parser.START_TAG) {
                switch (parser.getName()){
                    case "anime":
                        animeList.add(new Anime()); break;
                    case "series_title":
                        eventType = parser.next();
                        animeList.get(compt).setTitle(parser.getText());
                        break;
                    case "series_type":
                        eventType = parser.next();
                        animeList.get(compt).setType(parser.getText());
                        break;
                    case "series_status":
                        eventType = parser.next();
                        animeList.get(compt).setStatus(parser.getText());
                        break;
                    case "series_image":
                        eventType = parser.next();
                        animeList.get(compt).setImage(parser.getText());
                        break;
                }
            } else if (eventType == parser.END_TAG) {
                if(parser.getName().equals("anime")){
                    compt++;
                }
            } else if (eventType == parser.TEXT) {
            }
            eventType = parser.next();
        }
        return animeList;
    }
}
