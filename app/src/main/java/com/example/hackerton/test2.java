package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.example.hackerton.surface.StageView;

public class test2 extends AppCompatActivity {

    private FrameLayout stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        initView();

        // 서피스 뷰 생성 및
        SurfaceView view = new StageView(this);
        stage.addView(view);
    }

    private void initView() {
        stage = findViewById(R.id.stage);
    }
}