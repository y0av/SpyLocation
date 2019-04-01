package xyz.yoav.spylocation;

import android.content.Context;
import android.util.Log;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
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

    public void CreateNewGame(String creatorDisplayName) {
        GameManager.currentGame.gameName = creatorDisplayName;
        Map<String, Object> map = new HashMap<>();
        map.put("creator_dname",creatorDisplayName);
        dbRef.add(map)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "## added game with ID: " + documentReference.getId());
                    GameManager.currentGame.gameId = documentReference.getId();
                })
                .addOnFailureListener(e -> Log.w(TAG, "## Error adding document", e));
    }

    public void refreshAllOpenGames() {
        ArrayList<OpenGame> openGames = new ArrayList<>();
        dbRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            OpenGame game = new OpenGame(document.get("creator_dname").toString());
                            openGames.add(game);
                        }
                        if (mGamesListener!=null)
                            mGamesListener.onDataLoaded(openGames); //trigger all the listeners callbacks
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    public interface openGamesListener {
        public void onDataLoaded(ArrayList<OpenGame> openGames);
    }

    public void setOnDataLoadedListener(openGamesListener listener) {
        mGamesListener = listener;
    }

}
