package com.example.hackerton;

import android.media.MediaRecorder;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MediaRecorderDemo {

    private final String TAG = "MediaRecord";
    private MediaRecorder mMediaRecorder;
    public static final int MAX_LENGTH = 1000 * 60 * 10;/*When the maximum recording length // 1000 * 60 * 10;*/
    private String filePath;

    public MediaRecorderDemo(){
        this.filePath = "/dev/null";
    }

    public MediaRecorderDemo(File file) {
        this.filePath = file.getAbsolutePath();
    }

    private long startTime;
    private long endTime;

    /**
     * Use start recording amr format
     *
     * Recording files
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
     *
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
     *
     */
    private int BASE = 1;
    private int SPACE = 100;// sampling time interval

    private void updateMicStatus() {
        if (mMediaRecorder != null) {
            double ratio = (double)mMediaRecorder.getMaxAmplitude() /BASE;
            double db = 0;// db
            if (ratio > 1)
                db = 20 * Math.log10(ratio);
            Log.d(TAG,"Decibel value:"+db);
            mHandler.postDelayed(mUpdateMicStatusTimer, SPACE);
        }
    }

}
