package xyz.yoav.spylocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class JoinGameActivity extends AppCompatActivity {

    public static ArrayList<OpenGame> openGames;
    CloudFirestoreHelper cloudFirestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        RecyclerView rvOpenGames = findViewById(R.id.rvGames);

        cloudFirestoreHelper = new CloudFirestoreHelper(this);
        cloudFirestoreHelper.setOnDataLoadedListener(openGames -> {
            GamesViewAdapter adapter = new GamesViewAdapter(openGames);
            rvOpenGames.setAdapter(adapter);
        });
        cloudFirestoreHelper.refreshAllOpenGames();
        rvOpenGames.setLayoutManager(new LinearLayoutManager(this));
    }
}
