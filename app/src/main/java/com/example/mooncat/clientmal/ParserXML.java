package com.example.mooncat.clientmal;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class ParserXML {

    static LinkedList<Anime> parseUserAnimeList(String xml)throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        parser.setInput(is, "UTF-8");
        int compt=0;
        LinkedList<Anime> animeList = new LinkedList<>();
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()){
                    case "anime":
                        animeList.add(new Anime());
                        break;
                    case "series_animedb_id":
                        animeList.get(compt).setId(parser.getText());
                        break;
                    case "series_title":
                        parser.next();
                        animeList.get(compt).setTitle(parser.getText());
                        break;
                    case "series_type":
                        parser.next();
                        animeList.get(compt).setType(parser.getText());
                        break;
                    case "series_status":
                        parser.next();
                        animeList.get(compt).setStatus(parser.getText());
                        break;
                    case "series_image":
                        parser.next();
                        animeList.get(compt).setImage(parser.getText());
                        break;
                    case "series_synonyms":
                        parser.next();
                        animeList.get(compt).setSynonyms(parser.getText());
                        break;
                    case "series_start":
                        parser.next();
                        animeList.get(compt).setStart(parser.getText());
                        break;
                    case "series_end":
                        parser.next();
                        animeList.get(compt).setEnd(parser.getText());
                        break;
                    case "series_episodes" :
                        parser.next();
                        animeList.get(compt).setEpisodes(parser.getText());
                        break;
                    case "my_watched_episodes":
                        parser.next();
                        animeList.get(compt).setMyWatchedEpisodes(parser.getText());
                        break;
                    case "my_start_date":
                        parser.next();
                        animeList.get(compt).setMyStartDate(parser.getText());
                        break;
                    case "my_finish_date":
                        parser.next();
                        animeList.get(compt).setMyFinishDate(parser.getText());
                        break;
                    case "my_score":
                        parser.next();
                        animeList.get(compt).setMyScore(parser.getText());
                        break;
                    case "my_status":
                        parser.next();
                        animeList.get(compt).setMyStatus(parser.getText());
                        break;
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if(parser.getName().equals("anime")){
                    compt++;
                }
            }
            eventType = parser.next();
        }
        return animeList;
    }

    static LinkedList<Manga> parseUserMangaList(String xml)throws XmlPullParserException, IOException {
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
                        mangaList.add(new Manga());
                        break;
                    case "series_mangadb_id":
                        mangaList.get(compt).setId(parser.getText());
                        break;
                    case "series_title":
                        parser.next();
                        mangaList.get(compt).setTitle(parser.getText());
                        break;
                    case "series_type":
                        parser.next();
                        mangaList.get(compt).setType(parser.getText());
                        break;
                    case "series_status":
                        parser.next();
                        mangaList.get(compt).setStatus(parser.getText());
                        break;
                    case "series_image":
                        parser.next();
                        mangaList.get(compt).setImage(parser.getText());
                        break;
                    case "series_synonyms":
                        parser.next();
                        mangaList.get(compt).setSynonyms(parser.getText());
                        break;
                    case "series_start":
                        parser.next();
                        mangaList.get(compt).setStart(parser.getText());
                        break;
                    case "series_end":
                        parser.next();
                        mangaList.get(compt).setEnd(parser.getText());
                        break;
                    case "series_chapters":
                        parser.next();
                        mangaList.get(compt).setChapters(parser.getText());
                        break;
                    case "series_volumes":
                        parser.next();
                        mangaList.get(compt).setVolumes(parser.getText());
                        break;
                    case "my_read_chapters":
                        parser.next();
                        mangaList.get(compt).setMyReadChapters(parser.getText());
                        break;
                    case "my_read_volumes":
                        parser.next();
                        mangaList.get(compt).setMyReadVolumes(parser.getText());
                        break;
                    case "my_start_date":
                        parser.next();
                        mangaList.get(compt).setMyStartDate(parser.getText());
                        break;
                    case "my_finish_date":
                        parser.next();
                        mangaList.get(compt).setMyFinishDate(parser.getText());
                        break;
                    case "my_score":
                        parser.next();
                        mangaList.get(compt).setMyScore(parser.getText());
                        break;
                    case "my_status":
                        parser.next();
                        mangaList.get(compt).setMyStatus(parser.getText());
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

    static User parseUserAnimeInfo(String xml)throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        User user = new User();
        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        parser.setInput(is, "UTF-8");
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case "user_name":
                        parser.next();
                        user.setName(parser.getText());
                        break;
                    case "user_watching":
                        parser.next();
                        user.setWatchingAnime(parser.getText());
                        break;
                    case "user_completed":
                        parser.next();
                        user.setCompletedAnime(parser.getText());
                        break;
                    case "user_onhold":
                        parser.next();
                        user.setOnHoldAnime(parser.getText());
                        break;
                    case "user_dropped":
                        parser.next();
                        user.setDroppedAnime(parser.getText());
                        break;
                    case "user_plantowatch":
                        parser.next();
                        user.setPlanToWatchAnime(parser.getText());
                        break;
                    case "user_days_spent_watching":
                        parser.next();
                        user.setDaysSpentWatchingAnime(parser.getText());
                        break;
                }
            }
            eventType = parser.next();
        }
        return user;
    }

    static User parseUserMangaInfo(String xml) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        User user = new User();
        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        parser.setInput(is, "UTF-8");

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()){
                    case "user_name":
                        parser.next();
                        user.setName(parser.getText());
                        break;
                    case "user_reading":
                        parser.next();
                        user.setReadingManga(parser.getText());
                        break;
                    case "user_completed":
                        parser.next();
                        user.setCompletedManga(parser.getText());
                        break;
                    case "user_onhold":
                        parser.next();
                        user.setOnHoldManga(parser.getText());
                        break;
                    case "user_dropped":
                        parser.next();
                        user.setDroppedManga(parser.getText());
                        break;
                    case "user_plantoread":
                        parser.next();
                        user.setPlanToReadManga(parser.getText());
                        break;
                    case "user_days_spent_watching":
                        parser.next();
                        user.setDaysSpentWatchingManga(parser.getText());
                        break;
                }
            }
            eventType = parser.next();
        }
        return user;
    }
}
