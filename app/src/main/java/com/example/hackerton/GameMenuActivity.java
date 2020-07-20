package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameMenuActivity extends AppCompatActivity {
    Button btnStartAvoidGame; //장애물피하기 게임버튼
    Button btnStartWeightliftingGame; // 역도 게임 버튼
    Button btnStartBombGame;  //폭탄돌리기 게임버튼
    Button btnShowRecord; //기록보기 버튼
    Button btnFinish;   //종료버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        btnStartAvoidGame = findViewById(R.id.btnStartAvoidGame);
        btnStartWeightliftingGame = findViewById(R.id.btnStartWeightliftingGame);
        btnStartBombGame = findViewById(R.id.btnStartBombGame);
        btnShowRecord = findViewById(R.id.btnShowRecord);
        btnFinish = findViewById(R.id.btnFinish);

        btnStartAvoidGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        btnStartWeightliftingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Weightlifting.class);
                startActivity(intent);
            }
        });

        btnStartBombGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
            }
        });

        btnShowRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowRecordActivity.class);
                startActivity(intent);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}