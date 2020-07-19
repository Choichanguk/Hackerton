package com.example.hackerton.sql;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoadSql {
    private static final String TAG= "LoadSql";
    Context mContext = null;
    public LoadSql(Context mContext){
        this.mContext = mContext;
    }


    public void LoadSqlmethod(String key, String value) {


        try {
            GetPost_php task = new GetPost_php(mContext);
            task.execute("https://cd3222cd42e5.ngrok.io/hackerton.php",key,value);

            String callBackValue = task.get();

            if(callBackValue.isEmpty() || callBackValue.equals("") || callBackValue == null || callBackValue.contains("Error")) {
                Toast.makeText(mContext, "등록되지 않은 사용자이거나, 전송오류입니다.", Toast.LENGTH_SHORT).show();
            }
            // success
            else {
                JSONArray jsonArray = null;

                    jsonArray = new JSONArray(callBackValue);

                    for(int i=0; i < jsonArray.length(); i++){
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
}
