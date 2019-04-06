package xyz.yoav.spylocation;

import android.content.Context;
import android.util.Log;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CloudFirestoreHelper {

    private final String TAG = "CloudFirestoreHelper";
    private CollectionReference dbRef;
    private Context context;

    private openGamesListener mGamesListener;

    public CloudFirestoreHelper(Context context) {
        this.context = context;
        dbRef = FirebaseFirestore.getInstance().collection("open_games");
        mGamesListener = null;

    }

    public void CreateNewGame(Player creator) {
        GameManager.currentGame.gameName = creator.displayName;
        Map<String, Object> map = new HashMap<>();
        map.put("creator_dname",creator.displayName);
        map.put("timestamp", new Timestamp(new Date()));
        dbRef.add(map)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "## added game with ID: " + documentReference.getId());
                    GameManager.currentGame.hashId = documentReference.getId();
                    AddPlayerToCurrentGame(creator); //add creator as a player
                })
                .addOnFailureListener(e -> Log.w(TAG, "## Error adding document", e));
    }

    public void AddPlayerToCurrentGame(Player player) {
        if (GameManager.currentGame!=null) {
            Map<String, Object> map = new HashMap<>();
            map.put("display_name",player.displayName);
            dbRef.document(GameManager.currentGame.hashId).collection("players").add(map)
                    .addOnSuccessListener(documentReference -> {
                        player.hashId = documentReference.getId();
                    });
        }
    }

    public void refreshGamePlayersList(OpenGame game) {
        CollectionReference playersRef = dbRef.document(game.hashId).collection("players");
        playersRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Player player = new Player(document.getId(), document.get("display_name").toString());
                            game.players.add(player);
                        }
                    }
                });
    }

    public void refreshAllOpenGames() {
        JoinGameActivity.openGames = new ArrayList<>();
        dbRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            JoinGameActivity.openGames.add(parseGameDocument(document));
                        }
                        if (mGamesListener!=null)
                            mGamesListener.onDataLoaded(JoinGameActivity.openGames); //trigger all the listeners callbacks
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    public OpenGame parseGameDocument(QueryDocumentSnapshot doc) {
        OpenGame game = new OpenGame(doc.getId(), doc.get("creator_dname").toString());
        /*CollectionReference playersRef = doc.getReference().collection("players");
        playersRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Player player = new Player(document.getId(), document.get("display_name").toString());
                            game.players.add(player); //TODO i dont this it would work this way
                        }
                    }
                });*/


        return game;
    }

    public interface openGamesListener {
        public void onDataLoaded(ArrayList<OpenGame> openGames);
    }

    public void setOnDataLoadedListener(openGamesListener listener) {
        mGamesListener = listener;
    }

}
