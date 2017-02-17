package com.example.mooncat.clientmal;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Tools {

    public static String verifyCredentialsRequest() {
        return "https://myanimelist.net/api/account/veriy_credentials.xml";
    }

    public static String searchUserAnimeListRequest(String username) {
        return "https://myanimelist.net/malappinfo.php?u=" + username + "&status=all&type=anime";
    }

    public static String searchUserMangaListRequest(String username) {
        return "https://myanimelist.net/malappinfo.php?u=" + username + "&status=all&type=manga";
    }

    public static String searchAnime(String name) throws UnsupportedEncodingException {
        String requete = "https://myanimelist.net/api/anime/search.xml?q=";
        String nameEncoded = "" + URLEncoder.encode(name, "UTF-8");
        return requete + nameEncoded;
    }

    public static String searchManga(String name) throws UnsupportedEncodingException {
        String requete = "https://myanimelist.net/api/manga/search.xml?q=";
        String nameEncoded = "" + URLEncoder.encode(name, "UTF-8");
        return requete + nameEncoded;
    }

    public static String AddAnime(String id) throws UnsupportedEncodingException {
        String xmlEncoded = "" + URLEncoder.encode(UpdateAnimeValues("0", "6", "0"), "UTF-8");
        return "https://myanimelist.net/api/animelist/add/" + id + ".xml?data=" + xmlEncoded;
    }

    public static String AddManga(String id) throws UnsupportedEncodingException {
        String xmlEncoded = "" + URLEncoder.encode(UpdateMangaValues("0", "0", "6", "0"), "UTF-8");
        return "https://myanimelist.net/api/mangalist/add/" + id + ".xml?data=" + xmlEncoded;
    }

    public static String UpdateAnime(String id, String episode, String status, String score) throws UnsupportedEncodingException {
        String xmlEncoded = "" + URLEncoder.encode(UpdateAnimeValues(episode, status, score), "UTF-8");
        return "https://myanimelist.net/api/animelist/update/" + id + ".xml?data=" + xmlEncoded;
    }

    public static String UpdateManga(String id, String chapters, String volumes, String status, String score) throws UnsupportedEncodingException {
        String xmlEncoded = "" + URLEncoder.encode(UpdateMangaValues(chapters, volumes, status, score), "UTF-8");
        return "https://myanimelist.net/api/mangalist/update/" + id + ".xml?data=" + xmlEncoded;
    }

    public static String DeleteAnime(String id) {
        return "https://myanimelist.net/api/animelist/delete/" + id + ".xml";
    }

    public static String DeleteManga(String id) {
        return "https://myanimelist.net/api/mangalist/delete/" + id + ".xml";
    }

    public static String UpdateAnimeValues(String episode, String status, String score) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<entry>\n" +
                "\t<episode>" + episode + "</episode>\n" +
                "\t<status>" + status + "</status>\n" +
                "\t<score>" + score + "</score>\n" +
                "\t<storage_type></storage_type>\n" +
                "\t<storage_value></storage_value>\n" +
                "\t<times_rewatched></times_rewatched>\n" +
                "\t<rewatch_value></rewatch_value>\n" +
                "\t<date_start></date_start>\n" +
                "\t<date_finish></date_finish>\n" +
                "\t<priority></priority>\n" +
                "\t<enable_discussion></enable_discussion>\n" +
                "\t<enable_rewatching></enable_rewatching>\n" +
                "\t<comments></comments>\n" +
                "\t<tags>test tag, 2nd tag</tags>\n" +
                "</entry>";
        return xml;
    }

    public static String UpdateMangaValues(String chapters, String volumes, String status, String score) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<entry>\n" +
                "\t<chapter>" + chapters + "</chapter>\n" +
                "\t<volume>" + volumes + "</volume>\n" +
                "\t<status>" + status + "</status>\n" +
                "\t<score>" + score + "</score>\n" +
                "\t<times_reread></times_reread>\n" +
                "\t<reread_value></reread_value>\n" +
                "\t<date_start></date_start>\n" +
                "\t<date_finish></date_finish>\n" +
                "\t<priority></priority>\n" +
                "\t<enable_discussion></enable_discussion>\n" +
                "\t<enable_rereading></enable_rereading>\n" +
                "\t<comments></comments>\n" +
                "\t<scan_group></scan_group>\n" +
                "\t<tags></tags>\n" +
                "\t<retail_volumes></retail_volumes>\n" +
                "</entry>";
        return xml;
    }

    public static String readFile(Context context, String filename) {
        StringBuffer fileContent = new StringBuffer("");
        FileInputStream fis;
        try {
            fis = context.openFileInput(filename);

            byte[] buffer = new byte[1024];
            int n;
            while ((n = fis.read(buffer)) != -1) {
                fileContent.append(new String(buffer, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
