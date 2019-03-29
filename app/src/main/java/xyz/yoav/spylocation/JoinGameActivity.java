package xyz.yoav.spylocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class JoinGameActivity extends AppCompatActivity {

    ArrayList<OpenGame> openGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        RecyclerView rvOpenGames = findViewById(R.id.rvGames);

        openGames = OpenGame.createGamesList();
        GamesViewAdapter adapter = new GamesViewAdapter(openGames);
        rvOpenGames.setAdapter(adapter);
        rvOpenGames.setLayoutManager(new LinearLayoutManager(this));
    }
}
