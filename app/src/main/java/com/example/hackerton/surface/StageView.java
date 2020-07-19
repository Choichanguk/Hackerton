package com.example.hackerton.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.hackerton.R;

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


    public StageView(Context context) {
        super(context);

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
        player.setSize(new Point(200, 200));
        player.setPoint(new Point(200, height*3/4-100-mouseY));

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
//        int x = (int)event.getX();
//        int y = (int)event.getY();
//        mouseX = x;
        mouseY = 10;
        switch (keyAction){
//            case MotionEvent.ACTION_MOVE:
//
//                break;
            case MotionEvent.ACTION_UP:
                //mouseY = 10;
                player.jump(holder.getSurfaceFrame(), getContext());
                break;
            case MotionEvent.ACTION_DOWN:

                break;
        }
        // 함수 override 해서 사용하게 되면  return  값이  super.onTouchEvent(event) 되므로
        // MOVE, UP 관련 이벤트가 연이어 발생하게 할려면 true 를 반환해주어야 한다.
        return true;
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
            player.setDelta(0, 0);
            while(runThread != null) {
                // 그림판에 락을 걸어 잠근다.
                Canvas canvas = holder.lockCanvas();
                //Log.d("스레드","스레드의 x값 : "+width);
                //Log.d("스레드","스레드의 y값 : "+height);

                // Circle 이동
                x -= 10;
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

                Log.d("장애물 좌표 값 point", ""+ (y-100));


                float playYValue = player.getPointY();
                Log.d("player Y 좌표 값 point", ""+playYValue);
                if(x == 240){
                    if(playYValue == (y - 100)){
                        Log.d("좌표값이 같을 때", "있나");
                        break;
                    }
                }



            }
        }
    }

}

