package xyz.yoav.spylocation;

public class GameManager {
    public static OpenGame currentGame = new OpenGame("");
    private static Player me;
    private static boolean isCreator;

    public static void setPlayer(Player player, boolean creator) {
        me = player;
        isCreator = creator;
    }

    public static Player getMe() {
        return me;
    }
}

