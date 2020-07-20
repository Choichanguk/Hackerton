package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ranking_Weightlifting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking__weightlifting);

        /*
        허들의 종합랭킹을 확인할 수 있게 recyclerview를 전환해주는 소스이다.
        */
        Button ranking_hurdle = findViewById(R.id.ranking_hurdle);
        ranking_hurdle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), Ranking_Hurdle.class);
                        startActivity(intent);
                        //화면 전환을 부드럽게 해주는 소스이다.
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                }
        );
    }
}