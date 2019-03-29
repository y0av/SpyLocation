package xyz.yoav.spylocation;

import android.content.Context;
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

        // Return a new holder instance
        return new ViewHolder(gamesView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Get the data model based on position
        OpenGame game = mOpenGames.get(i);

        // Set item views based on your views and data model
        viewHolder.nameTextView.setText(game.getName());
    }

    @Override
    public int getItemCount() {
        return mOpenGames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.game_name);
        }
    }
}
