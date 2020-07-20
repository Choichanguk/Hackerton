package com.example.hackerton.sql;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 파라미터로 받는 url로 post 방식으로 데이터를 보내는 class
 */

public class PHPRequest extends Thread{
    String ID, PW, category1, record;
    private URL url;

    @Override
    public void run() {
        super.run();
        if(TextUtils.isEmpty(record)){

            push_userInfo(ID, PW, category1);
            Log.e("PHPRequest: ", "push_userInfo 실행");
            Log.e("PHPRequest: ", ID);
            Log.e("PHPRequest: ", PW);
            Log.e("PHPRequest: ", category1);
        }
        else {
            push_userRecord(ID, record);
            Log.e("PHPRequest: ", "push_userRecord 실행");
        }


    }

    /**
     *  새 아이디 서버에 저장할 때 불러지는 생성자
     */
    public PHPRequest(String url, String ID, String PW, String category1) throws MalformedURLException {
        this.url = new URL(url);
        this.ID = ID;
        this.PW = PW;
        this.category1 = category1;
    }

    /**
     *  유저 기록 서버에 저장할 때 불러지는 생성자
     */
    public PHPRequest(String url, String ID, String record) throws MalformedURLException {
        this.url = new URL(url);
        this.ID = ID;
        this.record = record;
    }


    /**
     * StringBuilder class 공부해야함
     * @param in
     * @return
     * @throws IOException
     */
    private String readStream(InputStream in) throws IOException {
        StringBuilder jsonHtml = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = null;

        while((line = reader.readLine()) != null)
            jsonHtml.append(line);

        reader.close();
        return jsonHtml.toString();
    }

    /**
     * 서버에 POST방식으로 데이터를 전달해주는 method
     * @param ID
     * @param PW
     * @param category1
     * @return
     */
    /**
     *  새 아이디 서버에 저장할 때 사용하는 메서드
     */
    public String push_userInfo(final String ID, final String PW,final String category1) {
        try {
            String postData = "ID=" + ID + "&" + "PW=" + PW + "&" + "category=" + category1;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Content-Type이란 request에 실어 보내는 데이터(body)의 type의 정보를 표현
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   //content type 공부해야함
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);     // 만약 request body를 포함한다면, true값으로 설정해주어야 한다.
            conn.setDoInput(true);

            Log.e("PHPRequest", "result");
            OutputStream outputStream = conn.getOutputStream();     //outputStream 객체에 write를 함으로써 데이터를 전송한다.
            outputStream.write(postData.getBytes("UTF-8"));

            outputStream.flush();   // 출력 스트림과 버퍼된 출력 바이트를 강제로 쓰게 한다.   -   버퍼란?
                                    //그리고 buffer가 다 차기 전에 프로그램을 종료하면 buffer에 들어있는 내용은 파일에 쓰여지지 않는다.
                                    //그 때 flush()를 호출하면 buffer의 내용이 파일에 쓰여진다.

            outputStream.close();

            String result = readStream(conn.getInputStream());
            conn.disconnect();
            Log.e("PHPRequest", result);
            return result;
        }
        catch (Exception e) {
            Log.e("PHPRequest", "request was failed.");
            return null;
        }
    }

    /**
     *  유저 기록 서버에 저장할 때 사용하는 메서드
     */
    public String push_userRecord(final String ID, final String Record) {
        try {
            String postData = "ID=" + ID + "&" + "Record=" + Record;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Content-Type이란 request에 실어 보내는 데이터(body)의 type의 정보를 표현
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   //content type 공부해야함
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);     // 만약 request body를 포함한다면, true값으로 설정해주어야 한다.
            conn.setDoInput(true);

            OutputStream outputStream = conn.getOutputStream();     //outputStream 객체에 write를 함으로써 데이터를 전송한다.
            outputStream.write(postData.getBytes("UTF-8"));

            outputStream.flush();   // 출력 스트림과 버퍼된 출력 바이트를 강제로 쓰게 한다.   -   버퍼란?
            //그리고 buffer가 다 차기 전에 프로그램을 종료하면 buffer에 들어있는 내용은 파일에 쓰여지지 않는다.
            //그 때 flush()를 호출하면 buffer의 내용이 파일에 쓰여진다.

            outputStream.close();

            String result = readStream(conn.getInputStream());
            conn.disconnect();
            return result;
        }
        catch (Exception e) {
            Log.e("PHPRequest", "request was failed.");
            return null;
        }
    }


}
