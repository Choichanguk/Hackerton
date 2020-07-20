package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hackerton.sql.LoadSql;

import java.util.ArrayList;

public class Ranking_Weightlifting extends AppCompatActivity {

    RecyclerView WL_recycler;
    Weightlifting_Record_Adepter adepter;
    ArrayList<Weightlifting_Record_Box> WL_item = new ArrayList<>();

    TextView my_ranking, my_nickname, my_score;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking__weightlifting);
        SharedClass sharedClass = new SharedClass();
        user_id = sharedClass.loadUserId(getApplicationContext());
        Log.e("user_id: ", user_id);
        user_id = "test3";




        my_ranking = findViewById(R.id.user_ranking_text);
        my_nickname = findViewById(R.id.user_nickname_text);
        my_score = findViewById(R.id.user_record_text);


        my_nickname.setText(user_id);
        my_ranking.setText("기록없음");
        my_score.setText("기록없음");

        LoadSql loadSql = new LoadSql(Ranking_Weightlifting.this);
        WL_item = loadSql.get_WL_record("https://4559a5a3a334.ngrok.io/hackerton_record.php", "WL");

        Log.e("list size: ", String.valueOf(WL_item.size()));
        WL_recycler = findViewById(R.id.WL_recycler);
        adepter = new Weightlifting_Record_Adepter();
        adepter.setItems(WL_item);
        WL_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        WL_recycler.setAdapter(adepter);



        for(int i=0; i < WL_item.size(); i++){
            if(WL_item.get(i).getId().equals(user_id)){

                    my_ranking.setText(""+(i+1));
                    my_nickname.setText(WL_item.get(i).getId());
                    my_score.setText(WL_item.get(i).getScore());

            }

        }

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