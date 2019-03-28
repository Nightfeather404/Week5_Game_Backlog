package com.example.backlog.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.backlog.R;
import com.example.backlog.model.Game;

/**
 * A class that lets the user add games by clicking on a floating Action Button (fab).
 */
public class AddGame extends AppCompatActivity {

    private Game game;
    private Spinner statusSelection;
    private EditText title;
    private EditText platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        initToolbar();
        initFAB();

        title = findViewById(R.id.titleEditor);
        platform = findViewById(R.id.platformEditor);
        statusSelection = findViewById(R.id.statusSpinner);

        game = getIntent().getParcelableExtra(MainActivity.UPDATE_GAME);

        if (game != null) {
            title.setText(game.getTitle());
            platform.setText(game.getPlatform());
            statusSelection.setSelection(((ArrayAdapter) statusSelection.getAdapter()).getPosition(game.getStatus()));
            this.setTitle(R.string.title_activity_add_game_update);
        } else
            this.setTitle(R.string.title_activity_add_game_new);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = title.getText().toString();
                String platformText = platform.getText().toString();
                String statusText = statusSelection.getSelectedItem().toString();

                if (game != null) {
                    if (!TextUtils.isEmpty(titleText) && !TextUtils.isEmpty(platformText)) {
                        game.setTitle(titleText);
                        game.setPlatform(platformText);
                        game.setStatus(statusText);

                        Intent resultIntent = new Intent();
                        resultIntent.putExtra(MainActivity.UPDATE_GAME, game);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    } else
                        Snackbar.make(view, "One of the fields is empty!!!", Snackbar.LENGTH_LONG).show();
                } else {

                    if (!TextUtils.isEmpty(titleText) && !TextUtils.isEmpty(platformText)) {
                        game = new Game(titleText, platformText, statusText);

                        Intent resultIntent = new Intent();
                        resultIntent.putExtra(MainActivity.NEW_GAME, game);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    } else
                        Snackbar.make(view, "One of the fields is empty!!!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

}
