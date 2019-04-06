package xyz.yoav.spylocation;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GamesViewAdapter extends
        RecyclerView.Adapter<GamesViewAdapter.ViewHolder>{

    // Store a member variable for the games
    private List<OpenGame> mOpenGames;
    private CloudFirestoreHelper cloudFirestoreHelper;

    // Pass in the games array into the constructor
    public GamesViewAdapter(List<OpenGame> games) {
        mOpenGames = games;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        // Inflate the custom layout
        View gamesView = inflater.inflate(R.layout.item_opengames, viewGroup, false);
        cloudFirestoreHelper = new CloudFirestoreHelper(viewGroup.getContext());

        // Return a new holder instance
        return new ViewHolder(gamesView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Get the data model based on position
        OpenGame game = mOpenGames.get(i);

        // Set item views based on your views and data model
        viewHolder.nameTextView.setText(game.getName());
        int count = 1;
        if (game.players != null) count = game.players.size() + 1;
        viewHolder.playersCount.setText( "999"); // +1 is to add the creator which is not listed in the players list
        viewHolder.itemView.setOnClickListener(v -> {
            joinGame(v.getContext(), game);
        });
    }

    public void joinGame(Context context, OpenGame game) { //TODO this should probably be in JoinGameActivity
        GameManager.currentGame = game;
        cloudFirestoreHelper.AddPlayerToCurrentGame(GameManager.getMe());
        Intent i = new Intent(context, GameLobbyActivity.class);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return mOpenGames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView playersCount;
        public View itemView;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.itemView = itemView;
            nameTextView = itemView.findViewById(R.id.game_name);
            playersCount = itemView.findViewById(R.id.players_count);
        }
    }


}
