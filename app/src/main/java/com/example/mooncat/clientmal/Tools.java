package com.example.mooncat.clientmal;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;

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
