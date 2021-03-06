package com.example.hackerton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackerton.sql.LoadSql;
import com.example.hackerton.sql.LoginConnecter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG= "RegisterActivity";

    Button btn_certificate_duple_id, btn_register;
    EditText register_id, password, password_re;

    String getData_url, pushData_url;


    boolean isCertificate = false;
    TabHost mTabHost = null;
    String myResult;

    private static String IP_ADDRESS = "http://192.168.254.129/mysql_android_getData.php";
    private static String TAG1 = "phptest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_register);

        register_id = findViewById(R.id.register_id);   // 회원가입 할 id를 입력받는 editText
        password = findViewById(R.id.password);         // 회원가입 할 pw를 입력받는 editText
        password_re = findViewById(R.id.password_re);   // 회원가입 할 pw를 재입력받는 editText

        btn_certificate_duple_id = findViewById(R.id.certificate_duple);  // 아이디 중복확인 버튼

        btn_certificate_duple_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean can_register = false;
                String input_id = register_id.getText().toString();     // editText로부터 입력받은 id 값값

               LoadSql loadSql = new LoadSql(RegisterActivity.this);
                can_register = loadSql.certificate_duple_info(input_id);


                /**
                 * 입력받은 id 중복확인하는 로직
                 * 중복확인 버튼을 누르면 사용가능 or 불가능에 대한 다이얼로그가 생성
                 */

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);

                if(can_register){
                    Log.e("result", "available");

                    builder.setTitle("아이디 중복확인").setMessage("사용가능한 아이디입니다. 사용하시겠습니까?");
                    builder.setPositiveButton("사용", yesButtonClickListener);
                    builder.setNegativeButton("취소", noButtonClickListener);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    Log.e("result", " non available");

                    builder.setTitle("아이디 중복확인").setMessage("이미 사용중인 아이디입니다.");
                    builder.setNegativeButton("확인", noButtonClickListener);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });


        /**
         * 회원가입 버튼  로직
         */
        btn_register = findViewById(R.id.register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = register_id.getText().toString();
                String pw = password.getText().toString();
                String pw_re = password_re.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                if(!isCertificate){

                    builder.setTitle("회원가입").setMessage("아이디 중복체크를 해주세요.");
                    builder.setNegativeButton("확인", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else if(TextUtils.isEmpty(pw) || TextUtils.isEmpty(pw_re)){

                    builder.setTitle("회원가입").setMessage("비밀번호를 입력해주세요.");
                    builder.setNegativeButton("확인", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else if(!pw.equals(pw_re)){

                    builder.setTitle("회원가입").setMessage("비밀번호가 일치하지 않습니다.");
                    builder.setNegativeButton("확인", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    LoadSql loadSql = new LoadSql(RegisterActivity.this);

                    try {
                        loadSql.save_usr_info_to_server(ID, pw);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    builder.setTitle("회원가입").setMessage("회원가입에 성공했습니다. 로그인 화면으로 이동합니다.");
                    builder.setNegativeButton("확인", successButtonClickListener);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
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

    /**
     * 다이얼로그 버튼 처리 이벤트 class
     */
    private DialogInterface.OnClickListener yesButtonClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            isCertificate = true;
        }
    };

    private DialogInterface.OnClickListener noButtonClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            isCertificate = false;
        }
    };

    private DialogInterface.OnClickListener successButtonClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    };


    /**
     * mySQL로 데이터 보내는 AsyncTask
     */


}
