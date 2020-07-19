package com.example.hackerton.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.hackerton.R;

import java.io.File;
import java.io.IOException;

public class StageView extends SurfaceView implements SurfaceHolder.Callback {
    private final String TAG = getClass().getSimpleName();
    SurfaceHolder holder;
    Thread runThread;
    private boolean goOnPlay = true;
    private Player player;

    private int mouseX = 0;
    private int mouseY = 0;






    int width = 0;
    int height = 0;

    int startY = 0;


    public StageView(Context context) {
        super(context);

        MediaRecorderDemo mediaRecorderDemo = new MediaRecorderDemo();
        mediaRecorderDemo.startRecord();

        // 화면 크기
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        // View를 생성할 때 Surface를 관리하는 Holder를 함께 생성
        // Holder를 상속 했기 때문에 getHolder 메소드 호출 가능
        holder = getHolder();

        // Holder에 콜백 추가
        // Surface View의 변경사항이 있을 때 마다 아래 3개 메소드 호출
        holder.addCallback(this);
        runThread = new DrawThread();


    }

    // Surface View가 생성될 때 호출
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: " + "onCreate");
        runThread.start();
        player = new Player();
        player.setImage(getResources().getDrawable(R.drawable.run));
        player.setSize(new Point(300, 300));
        startY = (height*3/4)-100;
        player.setPoint(new Point(200, startY));

    }

    // View가 변경 사항이 있을 때 ex) 크기가 바뀔 때 호출
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged: " + "changed");
    }

    // View가 종료 될 때 호출
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed: " + "destory");
        runThread = null;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int keyAction = event.getAction();

        Log.d("키엑션","이벤트 : "+event);

//        int x = (int)event.getX();
//        int y = (int)event.getY();
//        mouseX = x;
        mouseY = 10;
        switch (keyAction){
//            case MotionEvent.ACTION_MOVE:
//
//                break;
            case MotionEvent.ACTION_UP:
                Log.d("키엑션","엑션업 : "+MotionEvent.ACTION_UP);
                //mouseY = 10;
                player.jump(holder.getSurfaceFrame(), getContext());
                break;
            case MotionEvent.ACTION_DOWN:
                Log.d("키엑션","엑션다운 : "+MotionEvent.ACTION_DOWN);

                break;
        }
        // 함수 override 해서 사용하게 되면  return  값이  super.onTouchEvent(event) 되므로
        // MOVE, UP 관련 이벤트가 연이어 발생하게 할려면 true 를 반환해주어야 한다.
        return true;
    }

    public void jumpMethod() {
        player.jump(holder.getSurfaceFrame(), getContext());
    }

    class DrawThread extends Thread {
        float x=width, y=height*3/4, r=50;
        Paint paint;
        Paint paint1;
        public void run() {
            paint = new Paint();
            paint.setColor(Color.RED);
            paint1 = new Paint();
            paint1.setColor(Color.BLACK);
//            player.setDelta(0, 0);
            while(runThread != null) {
                // 그림판에 락을 걸어 잠근다.
                Canvas canvas = holder.lockCanvas();
                //Log.d("스레드","스레드의 x값 : "+width);
                //Log.d("스레드","스레드의 y값 : "+height);

                // Circle 이동
                x -= 20;
                //y += 0;
                canvas.drawColor(Color.WHITE);
                canvas.drawCircle(x, y, 50, paint);
                //canvas.drawCircle(200, height*3/4, 50, paint1);

                player.draw(canvas);

                // 화면을 넘어가면 초기화
                if (x < 0)      x = width;
                //if (y > height)     y = 0;

                // 그림판의 락을 푼다.
                holder.unlockCanvasAndPost(canvas);
                //Log.d("볼의 좌표 값 point", ""+player.getPoint());

                //Log.d("장애물 좌표 값 point", ""+ (y-100));
                //Log.d("장애물 x 좌표 값 point", ""+ x);


                float playYValue = player.getPointY();
                if(playYValue < startY) {
                    Log.d("y 다운 펑션", "지나감");
                    player.downY(holder.getSurfaceFrame(), getContext());
                }
//                Log.d("player Y 좌표 값 point", ""+playYValue);
//                Log.d("초기 Y 좌표 값 point", ""+startY);
                if(x < 430 && x > 390){
                    //Log.d("x 값 240", "지나감");
                    if(playYValue >= (y - 250) && playYValue <= (y + 100)){
                        Log.d("좌표값이 같을 때", "있나");
                        runThread = null;
                        break;
                    }
                }



            }
        }

    }


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

                if (db > 30) {
                    player.jump(holder.getSurfaceFrame(), getContext());
                }


            }
        }


    }




}
