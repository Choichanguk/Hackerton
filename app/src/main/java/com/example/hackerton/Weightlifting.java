package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.File;
import java.io.IOException;

public class Weightlifting extends AppCompatActivity implements Runnable {

    Button start_click_button;

    MediaRecorderDemo mediaRecorderDemo = new MediaRecorderDemo();
    /*
    게임 종료와 관련되어 있는 변수이다.
    */
    boolean game = true;


    /*
    게임 플레이 제한시간과 관련되어 있는 변수입니다.
    */
    int clock = 20;
    int clock_decrease = 0;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightlifting);


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

                        Thread t = new Thread(Weightlifting.this);
                        t.start();
                    }
                }
        );

    }

    @Override
    public void run() {


        imageview = (ImageView) findViewById(R.id.ready_motion);

        while (game) {
            while (clock != 0) {
                if(click > 96){
                    break;
                }
            /*
            시간이 10초~ 감소될 수 있게 적용되는 소스이다.
            */
                if (clock_decrease == 600) {
                    clock_decrease = 0;
                    clock--;
                    mHandler.sendEmptyMessage(0);
                }
                if (clock_decrease != 600) {
                    clock_decrease++;
                }

                if (click < 10) {
                    mHandler.sendEmptyMessage(2);
                } else if (click > 10 && click <= 20) {
                    mHandler.sendEmptyMessage(3);
                } else if (click > 20 && click <= 30) {
                    mHandler.sendEmptyMessage(4);
                } else if (click > 30 && click <= 40) {
                    mHandler.sendEmptyMessage(5);
                } else if (click > 40 && click <= 50) {
                    mHandler.sendEmptyMessage(6);
                } else if (click > 50 && click <= 60) {
                    mHandler.sendEmptyMessage(7);
                } else if (click > 60 && click <= 70) {
                    mHandler.sendEmptyMessage(8);
                } else if (click > 70 && click <= 80) {
                    mHandler.sendEmptyMessage(9);
                } else if (click > 80 && click <= 90) {
                    mHandler.sendEmptyMessage(10);
                } else if (click > 90 && click <= 92) {
                    mHandler.sendEmptyMessage(11);
                } else if (click > 92 && click <= 95) {
                    mHandler.sendEmptyMessage(12);
                } else if (click > 95) {
                    mHandler.sendEmptyMessage(13);
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mHandler.sendEmptyMessage(14);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /*
    Handler 작업
     */
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                clock_text = (TextView) findViewById(R.id.clock);
                imageview.setImageResource(R.drawable.a01);
                clock_text.setText("" + clock);
            }
            if (msg.what == 2) {
                imageview.setImageResource(R.drawable.a01);
            }
            if (msg.what == 3) {
                imageview.setImageResource(R.drawable.a02);
            }
            if (msg.what == 4) {
                imageview.setImageResource(R.drawable.a03);
            }
            if (msg.what == 5) {
                imageview.setImageResource(R.drawable.a04);
            }
            if (msg.what == 6) {
                imageview.setImageResource(R.drawable.a05);
            }
            if (msg.what == 7) {
                imageview.setImageResource(R.drawable.a06);
            }
            if (msg.what == 8) {
                imageview.setImageResource(R.drawable.a07);
            }
            if (msg.what == 9) {
                imageview.setImageResource(R.drawable.a08);
            }
            if (msg.what == 10) {
                imageview.setImageResource(R.drawable.a09);
            }
            if (msg.what == 11) {
                imageview.setImageResource(R.drawable.a10);
            }
            if (msg.what == 12) {
                imageview.setImageResource(R.drawable.a11);
            }
            if (msg.what == 13) {
                imageview.setImageResource(R.drawable.a12);
            }
            if (msg.what == 14) {
                start_click_button.setVisibility(View.VISIBLE);
                start_click_button.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                start_click_button.setVisibility(View.GONE);
                                clock = 20;
                                click = 0;
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