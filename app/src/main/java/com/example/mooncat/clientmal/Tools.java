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

    public static String DeleteAnime(String id) {
        return "https://myanimelist.net/api/animelist/delete/" + id + ".xml";
    }

    public static String DeleteManga(String id) {
        return "https://myanimelist.net/api/mangalist/delete/" + id + ".xml";
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
