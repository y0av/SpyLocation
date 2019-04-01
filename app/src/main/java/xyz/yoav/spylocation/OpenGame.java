package xyz.yoav.spylocation;

import java.util.ArrayList;

public class OpenGame {
    public String gameId;
    public String gameName;
    public Player creator;
    public Player[] players;

    public OpenGame(String name) {
        gameName = name;
    }

    public String getName() {
        return gameName;
    }

}
