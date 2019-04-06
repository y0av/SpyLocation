package xyz.yoav.spylocation;

public class Player {
    public String displayName;
    public String hashId;

    public Player(String displayName) {
        this.displayName = displayName;
    }

    public Player(String hashId ,String displayName) {
        this.displayName = displayName;
    }
}
