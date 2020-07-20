package com.example.hackerton.sql;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hackerton.Hurdle_Record_Box;
import com.example.hackerton.Weightlifting_Record_Box;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LoadSql {
    private static final String TAG = "LoadSql";
    private static final String URL = "http://4f11ce3a7f3c.ngrok.io/hackerton.php";
    private static final String URL1 = "http://4f11ce3a7f3c.ngrok.io/hackerton_save.php";   // 서버로 유저 정보, 기록 저장시킬때 사용하는 URL

    Context mContext = null;

    public LoadSql(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 테이블의 컬럼 key의 value를 가져오는 메서드
     */

    public void Load_one_usr_data(String key, String value) {
        try {
            GetPost_php task = new GetPost_php(mContext);
            task.execute(URL, key, value);

            String callBackValue = task.get();

            if (callBackValue.isEmpty() || callBackValue.equals("") || callBackValue == null || callBackValue.contains("Error")) {
                Toast.makeText(mContext, "등록되지 않은 사용자이거나, 전송오류입니다.", Toast.LENGTH_SHORT).show();
            }
            // success
            else {
                JSONArray jsonArray = null;

                jsonArray = new JSONArray(callBackValue);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("ID");
                    String pw = jsonObject.getString("PW");
                }


                // TODO : callBackValue를 이용해서 코드기술
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 유저 전체 id, pw 가져오는 메서드
     */
//    public void Load_all_usr_data() {
//        try {
//            GetPost_php task = new GetPost_php(mContext);
//            task.execute(URL);
//
//            String callBackValue = task.get();
//
//            if(callBackValue.isEmpty() || callBackValue.equals("") || callBackValue == null || callBackValue.contains("Error")) {
//                Toast.makeText(mContext, "등록되지 않은 사용자이거나, 전송오류입니다.", Toast.LENGTH_SHORT).show();
//            }
//            // success
//            else {
//                JSONArray jsonArray = null;
//
//                jsonArray = new JSONArray(callBackValue);
//
//                for(int i=0; i < jsonArray.length(); i++){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    String id = jsonObject.getString("ID");
//                    String pw = jsonObject.getString("PW");
//                    Log.e(TAG, "id: " + id);
//                    Log.e(TAG, "pw: " + pw);
//                }
//                // TODO : callBackValue를 이용해서 코드기술
//            }
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 로그인 시 입력한 id, pw 정보를 조회 후 로그인 가능 or 불가능 여부를 boolean 값으로 반환하는 메서드
     */
    public boolean certificate_login_info(String url, String input_id, String input_pw) {
        boolean can_login = false;
        LoginConnecter login_task = new LoginConnecter(url);
        Log.e(TAG, url);
        login_task.start();

        try {
            login_task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = login_task.getResult();
//                Log.e(TAG, "result: " + result);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(result);
            Log.e(TAG, String.valueOf(jsonArray));
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("ID");
                String pw = jsonObject.getString("PW");

                Log.e(TAG, "ID: " + id + "PW: " + pw);
                Log.e(TAG, "input_it: " + input_id + "input_pw: " + input_pw);
                if (input_id.equals(id) && input_pw.equals(pw)) {
                    can_login = true;
                    break;

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return can_login;
    }

    /**
     * 회원가입 시 입력한 id가 다른 유저의 id와 중복되면 false, 안되면 true를 return하는 메서드
     */
    public boolean certificate_duple_info(String input_id) {
        boolean can_register = false;
        LoginConnecter login_task = new LoginConnecter(URL);
        Log.e(TAG, URL);
        login_task.start();

        try {
            login_task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = login_task.getResult();

        JSONArray jsonArray = null;
        ArrayList id_list = new ArrayList();
        try {
            jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("ID");
                id_list.add(id);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!id_list.contains(input_id)) {
            can_register = true;
        } else {
            can_register = false;
        }

        return can_register;
    }

    /**
     *
     */
    public void save_usr_info_to_server(String user_id, String user_pw) throws MalformedURLException {
        GetPost_php task = new GetPost_php(mContext);
        task.execute(URL1, "ID", user_id, "PW", user_pw);

    }

    public ArrayList<Hurdle_Record_Box> get_hurdle_record(String url, String what) {
        ArrayList<Hurdle_Record_Box> hurdle_item = new ArrayList<>();

        try {
            GetPost_php task = new GetPost_php(mContext);
            task.execute(url, what);

            String callBackValue = task.get();
            Log.e("task", callBackValue);
            if (callBackValue.isEmpty() || callBackValue.equals("") || callBackValue == null || callBackValue.contains("Error")) {
                Toast.makeText(mContext, "등록되지 않은 사용자이거나, 전송오류입니다.", Toast.LENGTH_SHORT).show();
            }
            // success
            else {
                JSONArray jsonArray = null;

                jsonArray = new JSONArray(callBackValue);

                for (int i = 0; i < jsonArray.length(); i++) {

                    Hurdle_Record_Box item = new Hurdle_Record_Box();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    String id = jsonObject.getString("ID");
                    String score = jsonObject.getString("Record_hurdle");
                    if(!score.equals("null")){
                        item.setId(id);
                        item.setScore(score);
                        hurdle_item.add(item);
                        Log.e("task", id);
                        Log.e("task", score);
                    }
                }


                // TODO : callBackValue를 이용해서 코드기술
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hurdle_item;
    }

    public ArrayList<Weightlifting_Record_Box> get_WL_record(String url, String what) {
        ArrayList<Weightlifting_Record_Box> WL_item = new ArrayList<>();

        try {
            GetPost_php task = new GetPost_php(mContext);
            task.execute(url, what);

            String callBackValue = task.get();
            Log.e("task", callBackValue);
            if (callBackValue.isEmpty() || callBackValue.equals("") || callBackValue == null || callBackValue.contains("Error")) {
                Toast.makeText(mContext, "등록되지 않은 사용자이거나, 전송오류입니다.", Toast.LENGTH_SHORT).show();
            }
            // success
            else {
                JSONArray jsonArray = null;

                jsonArray = new JSONArray(callBackValue);

                for (int i = 0; i < jsonArray.length(); i++) {

                    Weightlifting_Record_Box item = new Weightlifting_Record_Box();

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("ID");
                    //Record 에따라서 가져옴 허들오어 wl
                    String score = jsonObject.getString("Record_WL");
                    if(!score.equals("null")){
                        item.setId(id);
                        item.setScore(score);
                        WL_item.add(item);
                        Log.e("task", id);
                        Log.e("task", score);
                    }
                }


                // TODO : callBackValue를 이용해서 코드기술
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return WL_item;
    }



}
