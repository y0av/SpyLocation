package xyz.yoav.spylocation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlayersViewAdapter extends
        RecyclerView.Adapter<PlayersViewAdapter.ViewHolder>{


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        // Inflate the custom layout
        View playerView = inflater.inflate(R.layout.item_player, viewGroup, false);
        //cloudFirestoreHelper = new CloudFirestoreHelper(viewGroup.getContext());

        // Return a new holder instance
        return new ViewHolder(playerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Player player = GameManager.currentGame.players.get(i);
        viewHolder.nameTextView.setText(player.displayName);
    }

    @Override
    public int getItemCount() {
        return GameManager.currentGame.players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            nameTextView = itemView.findViewById(R.id.player_name);
        }
    }
}
