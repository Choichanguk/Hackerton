package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hackerton.R;

public class test1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        MediaRecorderDemo mediaRecorderDemo= new MediaRecorderDemo();

        mediaRecorderDemo.startRecord();

    }
}