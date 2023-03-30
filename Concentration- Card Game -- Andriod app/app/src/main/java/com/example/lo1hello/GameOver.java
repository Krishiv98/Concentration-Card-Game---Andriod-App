package com.example.lo1hello;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        TextView txtGameOver = findViewById(R.id.txtGameOver);
        String tempText = "You Won after " + MainActivity.numberOfGuesses + " Guesses";
        txtGameOver.setText(tempText);

        Button btnNewGame = findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.flipCards();

                finish();
            }
        });
    }

}
