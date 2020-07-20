package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hackerton.sql.LoadSql;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Weightlifting extends AppCompatActivity  {

    Button start_click_button;

    MediaRecorderDemo mediaRecorderDemo = new MediaRecorderDemo();

    /*
    게임 동작에 대한 진도율과 관련되어 있는 변수입니다.
    */
    int percent = 0;
    TextView percent_text;

    int startTime;
    int endTime;


    /*
    게임 종료와 관련되어 있는 변수이다.
    */
    boolean game = true;
    boolean end = true;

    // MediaPlayer 객체생성
    MediaPlayer mediaPlayer;
//    int i = 0;

    /*
    게임 플레이 제한시간과 관련되어 있는 변수입니다.
    */
    int clock = 20;
//    int clock_decrease = 0;
    TextView clock_text;

    /*
    클릭한 횟수가 증가와 관련되어 있는 변수입니다.
    */
    int click = 0;
    TextView click_count;

    /*
    역도 모션과 관련되어 있는 변수입니다.
    */
    ImageView imageview = null;

    /*
    프로그레스바 pgb
    */
    ProgressBar pgb;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightlifting);

        clock_text = findViewById(R.id.clock);

        pgb = findViewById(R.id.progressbar);

        pgb.setProgress(0);
        pgb.setMax(90);


        mediaRecorderDemo.startRecord();

        start_click_button = (Button) findViewById(R.id.start_click_button);

        start_click_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        start_click_button.setVisibility(View.GONE);

                        thread t = new thread();
                        t.start();
                    }
                }
        );

    }
    public class thread extends Thread{
        @Override
        public void run() {


            imageview = (ImageView) findViewById(R.id.ready_motion);
            int i=0;
            while (game) {

                loop1:while (clock != 0) {
//                Message msg = new Message();
//                msg.arg1 = i++;
//                mHandler.sendMessage(msg);
                    if(click > 96){
                        Log.e("break", "break");
                        break loop1;
                    }

            /*
            시간이 10초~ 감소될 수 있게 적용되는 소스이다.
            */
//                if (clock_decrease == 600) {
//                    clock_decrease = 0;
//                    clock--;
//                    Message msg = new Message();
//                    msg.arg1 = i++;
//                    msg.what = 0;
//                    mHandler.sendMessage(msg);
//                }
//                if (clock_decrease != 600) {
//                    clock_decrease++;
//                }



                    if (click < 10) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 2;
                        mHandler.sendMessage(msg);

                    } else if (click > 10 && click <= 20) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 3;
                        mHandler.sendMessage(msg);

                    } else if (click > 20 && click <= 30) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 4;
                        mHandler.sendMessage(msg);

                    } else if (click > 30 && click <= 40) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 5;
                        mHandler.sendMessage(msg);

                    } else if (click > 40 && click <= 50) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 6;
                        mHandler.sendMessage(msg);

                    } else if (click > 50 && click <= 60) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 7;
                        mHandler.sendMessage(msg);

                    } else if (click > 60 && click <= 70) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 8;
                        mHandler.sendMessage(msg);

                    } else if (click > 70 && click <= 80) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 9;
                        mHandler.sendMessage(msg);

                    } else if (click > 80 && click <= 90) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 10;
                        mHandler.sendMessage(msg);

                    } else if (click > 90 && click <= 92) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 11;
                        mHandler.sendMessage(msg);

                    } else if (click > 92 && click <= 95) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 12;
                        mHandler.sendMessage(msg);

                    } else if (click > 95) {
                        Message msg = new Message();
                        msg.arg1 = i++;
                        msg.what = 13;
                        mHandler.sendMessage(msg);

                    }
                    try {
                        Thread.sleep(10);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                endTime = (int) System.currentTimeMillis();
                Message msg = new Message();
                msg.arg1 = i++;
                msg.what = 14;
                mHandler.sendMessage(msg);
                game = false;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    Handler 작업
     */
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            final int mSec = msg.arg1 % 100;
            final int sec = (msg.arg1 / 100) % 60;
            final int min = (msg.arg1 / 100) / 60;
            result = String.format("%02d:%02d:%02d",  min, sec, mSec);
            clock_text.setText(result);
//            Log.i("result", "result: " + i);
            if (msg.what == 0) {
                clock_text = (TextView) findViewById(R.id.clock);
                imageview.setImageResource(R.drawable.a01);
            }
            if (msg.what == 2) {
                imageview.setImageResource(R.drawable.a01);
                percent=0;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 3) {
                imageview.setImageResource(R.drawable.a02);
                percent=8;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 4) {
                imageview.setImageResource(R.drawable.a03);
                percent=16;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 5) {
                imageview.setImageResource(R.drawable.a04);
                percent=24;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 6) {
                imageview.setImageResource(R.drawable.a05);
                percent=32;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 7) {
                imageview.setImageResource(R.drawable.a06);
                percent=40;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 8) {
                imageview.setImageResource(R.drawable.a07);
                percent=48;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 9) {
                imageview.setImageResource(R.drawable.a08);
                percent=56;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 10) {
                imageview.setImageResource(R.drawable.a09);
                percent=67;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 11) {
                imageview.setImageResource(R.drawable.a10);
                percent=75;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 12) {
                imageview.setImageResource(R.drawable.a11);
                percent=84;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 13) {
                imageview.setImageResource(R.drawable.a12);
                percent=95;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");
            }
            if (msg.what == 14) {
                int save_time= msg.arg1;
                SharedClass sharedClass = new SharedClass();
                String user_id = sharedClass.loadUserId(getApplicationContext());
                Log.e("역도 id: ", user_id);

                LoadSql loadSql = new LoadSql(getApplicationContext());
                try {
                    loadSql.save_record_to_server("https://4559a5a3a334.ngrok.io/hackerton_record.php", "WL", user_id, "Record_WL" ,""+save_time);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                imageview.setImageResource(R.drawable.a12);
                mediaPlayer = MediaPlayer.create(Weightlifting.this, R.raw.crap);
                mediaPlayer.start();
                percent=100;
                percent_text = (TextView) findViewById(R.id.percent_text);
                percent_text.setText(percent+"%");

                end = false;
                start_click_button.setVisibility(View.VISIBLE);
                start_click_button.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // 정지버튼
                                mediaPlayer.stop();
                                // 초기화
                                mediaPlayer.reset();
                                end = true;
                                start_click_button.setVisibility(View.GONE);
                                click = 0;
                                game = true;
                                thread t = new thread();
                                t.start();
                            }
                        }
                );
            }
        }
    };


    public class MediaRecorderDemo {

        private final String TAG = "MediaRecord";
        private MediaRecorder mMediaRecorder;
        public static final int MAX_LENGTH = 1000 * 60 * 10;/*When the maximum recording length // 1000 * 60 * 10;*/
        private String filePath;
        public int gar;


        public MediaRecorderDemo() {
            this.filePath = "/dev/null";
        }

        public MediaRecorderDemo(File file) {
            this.filePath = file.getAbsolutePath();
        }

        private long startTime;
        private long endTime;

        /**
         * Use start recording amr format
         * <p>
         * Recording files
         *
         * @return
         */
        public void startRecord() {
      /*  // start recording
        / * ①Initial: MediaRecorder instantiate objects * /*/
            if (mMediaRecorder == null)
                mMediaRecorder = new MediaRecorder();
            try {
                /* ②setAudioSource/setVedioSource */
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// set the microphone
                /* / * Set ② encoded audio file: AAC / AMR_NB / AMR_MB / Default sound (waveform) samples * /*/
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                /*
                 * Output file format setting ②: THREE_GPP / MPEG-4 / RAW_AMR / Default THREE_GPP (3gp format
                 *, H263 videos / ARM Audio Coding), MPEG-4, RAW_AMR (only support audio and audio encoding requirements AMR_NB)
                 */
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                /*/ * ③ ready * /*/
                mMediaRecorder.setOutputFile(filePath);
                mMediaRecorder.setMaxDuration(MAX_LENGTH);
                mMediaRecorder.prepare();
                /* ④ start */
                mMediaRecorder.start();
                // AudioRecord audioRecord.
                /* * * Acquisition start time */
                startTime = System.currentTimeMillis();
                updateMicStatus();
                Log.i("ACTION_START", "startTime" + startTime);
            } catch (IllegalStateException e) {
                Log.i(TAG,
                        "call startAmr(File mRecAudioFile) failed!"
                                + e.getMessage());
            } catch (IOException e) {
                Log.i(TAG,
                        "call startAmr(File mRecAudioFile) failed!"
                                + e.getMessage());
            }
        }

        /**
         * Stop recording
         */
        public long stopRecord() {
            if (mMediaRecorder == null)
                return 0L;
            endTime = System.currentTimeMillis();
            Log.i("ACTION_END", "endTime" + endTime);
            mMediaRecorder.stop();
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            Log.i("ACTION_LENGTH", "Time" + (endTime - startTime));
            return endTime - startTime;
        }

        private final Handler mHandler = new Handler();
        private Runnable mUpdateMicStatusTimer = new Runnable() {
            public void run() {
                updateMicStatus();
            }
        };

        /**
         * Updated microphone status
         */
        private int BASE = 170;
        private int SPACE = 100;// sampling time interval

        private void updateMicStatus() {
            if (mMediaRecorder != null) {
                double ratio = (double) mMediaRecorder.getMaxAmplitude() / BASE;

                double db = 0;// db
                if (ratio > 1)
                    db = 20 * Math.log10(ratio);
                Log.d(TAG, "Decibel value:" + db);
                mHandler.postDelayed(mUpdateMicStatusTimer, SPACE);

                if (db > 38) {
                    click++;
                    click++;
                    click++;
                }else if (db <= 38){
                    if(end){
                        click--;
                    }

                }



                pgb.setProgress((int) db * 2);

            }
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaRecorderDemo.stopRecord();
    }
}