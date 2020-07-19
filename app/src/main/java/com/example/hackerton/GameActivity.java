package com.example.hackerton;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void startGame(View view){
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }
}