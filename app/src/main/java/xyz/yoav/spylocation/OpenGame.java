package xyz.yoav.spylocation;

import java.util.ArrayList;
import java.util.List;

public class OpenGame {
    public String hashId;
    public String gameName;
    public Player creator;
    public List<Player> players;

    public OpenGame(String name) {

        gameName = name;
    }

    public OpenGame(String hashId ,String name) {
        this.hashId = hashId;
        gameName = name;
    }

    public String getName() {
        return gameName;
    }

}
