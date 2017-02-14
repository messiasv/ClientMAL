package com.example.mooncat.clientmal;

public class Tools {

    public static String verifyCredentialsRequest() {
        return "https://myanimelist.net/api/account/veriy_credentials.xml";
    }

    public static String searchUserAnimeListRequest(String username) {
        return "https://myanimelist.net/malappinfo.php?u=" + username + "&status=all&type=anime";
    }

    public static String SearchUserMangaListRequest(String username) {
        return "https://myanimelist.net/malappinfo.php?u=" + username + "&status=all&type=manga";
    }
}
