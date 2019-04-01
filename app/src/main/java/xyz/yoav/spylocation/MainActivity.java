package xyz.yoav.spylocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CloudFirestoreHelper cloudFirestoreHelper;
    SharedPrefsHelper spHelper;
    TextView displayNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spHelper = new SharedPrefsHelper(this);
        cloudFirestoreHelper = new CloudFirestoreHelper(this);
        displayNameText = findViewById(R.id.tv_display_name);
        if (isVailudDisplayName(spHelper.getDisplayName()))
            displayNameText.setText(spHelper.getDisplayName()); //autofill users display name from last time they used the app
        Button createGame = findViewById(R.id.btn_create_game);
        Button joinGame = findViewById(R.id.btn_join_game);

        createGame.setOnClickListener(v -> createNewGame());
        joinGame.setOnClickListener(v -> joinGame());
    }

    public void createNewGame() {
        String displayName = displayNameText.getText().toString();
        if (isVailudDisplayName(displayName)) {
            cloudFirestoreHelper.CreateNewGame(displayName);
            spHelper.setDisplayName(displayName);
            //Intent i = new Intent(getApplicationContext(), .class);
            //startActivity(i);
        }
    }
    public void joinGame() {
        if (isVailudDisplayName(displayNameText.getText().toString())) {
            Intent i = new Intent(getApplicationContext(), JoinGameActivity.class);
            startActivity(i);
        }
    }

    private boolean isVailudDisplayName(String dn) {
        return dn.length() > 3; //TODO check more stuff
    }
}
