package com.example.hackerton;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MediaRecordDemo2 extends AppCompatActivity{
    Double Max_DB = 0.0;

    TextView mStatusView;
    MediaRecorder mRecorder;
    Thread runner;
    private static double mEMA = 0.0;
    static final private double EMA_FILTER = 0.6;

    final Runnable updater = new Runnable(){

        public void run(){
            updateTv();
        };
    };

    final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediarecorddemo2);

        mStatusView = (TextView) findViewById(R.id.status);

        if (runner == null)
        {
            runner = new Thread(){
                public void run()
                {
                    while (runner != null)
                    {
                        try
                        {
                            Thread.sleep(500);
                            Log.i("Noise", "Tock");
                        } catch (InterruptedException e) { };
                        mHandler.post(updater);
                    }
                }
            };
            runner.start();
            Log.d("Noise", "start runner()");
        }

    }

    public void onResume()
    {
        super.onResume();
        startRecorder();
    }

    public void onPause()
    {
        super.onPause();
        stopRecorder();
    }

    public void startRecorder(){
        if (mRecorder == null)
        {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            try
            {
                mRecorder.prepare();
            }catch (java.io.IOException ioe) {
                Log.e("[Monkey]", "IOException: " + Log.getStackTraceString(ioe));

            }catch (SecurityException e) {
                Log.e("[Monkey]", "SecurityException: " + Log.getStackTraceString(e));
            }
            try
            {
                mRecorder.start();
            }catch (SecurityException e) {
                Log.e("[Monkey]", "SecurityException: " + Log.getStackTraceString(e));
            }

            //mEMA = 0.0;
        }

    }

    public void stopRecorder() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public void updateTv(){
        if(Max_DB < getAmplitudeEMA()){
            Max_DB = getAmplitudeEMA();
            mStatusView.setText(Double.toString(Max_DB) + " dB");
        }

    }
    public double soundDb(double ampl){
        return  20 * Math.log10(getAmplitudeEMA() / ampl);
    }
    public double getAmplitude() {
        if (mRecorder != null)
            return  (mRecorder.getMaxAmplitude());
        else
            return 0;

    }
    public double getAmplitudeEMA() {
        double amp =  getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA  ;
        return mEMA;
    }

}
