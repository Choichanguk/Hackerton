package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameMenuActivity extends AppCompatActivity {
    Button btnStartAvoidGame; //장애물피하기 게임버튼
    Button btnStartBombGame;  //폭탄돌리기 게임버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        btnStartAvoidGame = findViewById(R.id.btnStartAvoidGame);
        btnStartBombGame = findViewById(R.id.btnStartBombGame);

        btnStartAvoidGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
            }
        });

        btnStartBombGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
            }
        });
    }
}