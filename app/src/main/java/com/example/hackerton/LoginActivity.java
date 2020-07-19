package com.example.hackerton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackerton.sql.LoadSql;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG= "LoginActivity";
    Button btn_register, btn_login, test;
    EditText id, password;
//
//    URLConnector login_task;
    String getData_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_login);


        /*로드시킬 데이터 키(컬럼)-벨류(값) 입력하여 값을 받아옴
        해당 벨류(값)이 들어간 row값을 가져옴*/
        test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "ID"; // 컬럼
                String value = "test1"; // 값
                LoadSql loadSql = new LoadSql(LoginActivity.this);
                loadSql.LoadSqlmethod(key, value);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

}

