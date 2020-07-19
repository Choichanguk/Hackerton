package com.example.hackerton;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackerton.sql.LoadSql;
import com.example.hackerton.sql.LoginConnecter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG= "LoginActivity";
    private static final String URL = "http://8dc0522ff79e.ngrok.io/hackerton.php";
    Button btn_register, btn_login, test;
    EditText id, password;



    String getData_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_login);

        /**
         * 회원가입 버튼
         */
        btn_register = findViewById(R.id.register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


        id = findViewById(R.id.id);

        password = findViewById(R.id.password);


        /**
         * 로그인 버튼
         */
        btn_login = findViewById(R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 유저가 입력한 id값을 String으로 받아온다.
                String input_id = id.getText().toString();

                // 유저가 입력한 pw값을 String으로 받아온다.
                String input_pw = password.getText().toString();

                boolean can_login = false;

                LoadSql loadSql = new LoadSql(LoginActivity.this);
                can_login = loadSql.certificate_login_info(URL, input_id, input_pw);

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                /**
                 * 로그인 버튼 누르면 로그인 성공 or 실패를 알려주는 다이얼
                 */
                if(can_login){

                    /**
                     * 로그인 성공 시 로그인 상태와 유저 ID를 쉐어드에 저장.
                     */
                    SharedClass sharedClass = new SharedClass();
                    sharedClass.saveUserId(LoginActivity.this, input_id);
                    sharedClass.saveLoginStatus(LoginActivity.this, true);

                    Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
                else{
                    builder.setTitle("로그인").setMessage("아이디, 비밀번호를 확인해주세요.");
                    builder.setNegativeButton("확인", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });


//        /*로드시킬 데이터 키(컬럼)-벨류(값) 입력하여 값을 받아옴
//        해당 벨류(값)이 들어간 row값을 가져옴*/
//        test = findViewById(R.id.test);
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String key = "ID"; // 컬럼
//                String value = "test1"; // 값
//                LoadSql loadSql = new LoadSql(LoginActivity.this);
//                loadSql.LoadSqlmethod(key, value);
//            }
//        });



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

