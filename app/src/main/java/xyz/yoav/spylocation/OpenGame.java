package xyz.yoav.spylocation;

import java.util.ArrayList;

public class OpenGame {
    private String gameName;

    public OpenGame(String name) {
        gameName = name;
    }

    public String getName() {
        return gameName;
    }

    public static ArrayList<OpenGame> createGamesList() {
        ArrayList<OpenGame> games = new ArrayList<>();

        //for (int i = 1; i <= numContacts; i++) {

        games.add(new OpenGame("game1"));
        games.add(new OpenGame("game1"));
        games.add(new OpenGame("game1"));
        //}

        return games;
    }

}
