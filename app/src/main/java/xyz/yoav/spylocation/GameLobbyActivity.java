package xyz.yoav.spylocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class GameLobbyActivity extends AppCompatActivity {

    CloudFirestoreHelper cloudFirestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);

        RecyclerView rvPlayers = findViewById(R.id.rv_players);

        cloudFirestoreHelper = new CloudFirestoreHelper(this);
        cloudFirestoreHelper.setOnPlayersDataLoadedListener(players -> {
            PlayersViewAdapter adapter = new PlayersViewAdapter();
            rvPlayers.setAdapter(adapter);
        });
        cloudFirestoreHelper.refreshGamePlayersList();
        //rvOpenGames.setLayoutManager(new LinearLayoutManager(this));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        rvPlayers.setLayoutManager(layoutManager);
        //rvPlayers.setLayoutManager(new GridLayoutManager(this,4));
    }
}
