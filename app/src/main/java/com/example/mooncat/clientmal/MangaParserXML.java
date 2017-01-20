package com.example.mooncat.clientmal;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;


class MangaParserXML {

    static LinkedList<Manga> parseList(String xml)throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        parser.setInput(is, "UTF-8");
        int compt=0;
        LinkedList<Manga> mangaList = new LinkedList<>();
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
             if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()){
                    case "manga":
                        mangaList.add(new Manga()); break;
                    case "series_title":
                        eventType = parser.next();
                        mangaList.get(compt).setTitle(parser.getText());
                        break;
                    case "series_type":
                        eventType = parser.next();
                        mangaList.get(compt).setType(parser.getText());
                        break;
                    case "series_status":
                        eventType = parser.next();
                        mangaList.get(compt).setStatus(parser.getText());
                        break;
                    case "series_image":
                        eventType = parser.next();
                        mangaList.get(compt).setImage(parser.getText());
                        break;
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if(parser.getName().equals("manga")){
                    compt++;
                }
            }
            eventType = parser.next();
        }
        return mangaList;
    }
}
