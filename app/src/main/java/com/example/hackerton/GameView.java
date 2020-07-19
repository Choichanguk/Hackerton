package com.example.hackerton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameView extends View {

    //This is our custom View class
    Handler handler; // Handler is required to schedule a runnable after some delay
    Runnable runnable;
    final int UPDATE_MILLIS=20;
    Bitmap background;
    Bitmap toptube, bottomtube;
    Display display;
    Point point;
    int dWidth, dHeight; //Device width and height respectively
    Rect rect;
    //Lets create a Bitmap array for the bird
    Bitmap [] birds;
    //We need an integer variable to keep track of bird image
    int birdFrame=0;
    int velocity=50, gravity=3; // Let's play around with these values
    //We need to keep track of bird position
    int birdX, birdY;
    boolean gameState = false;
    int gap = 400; //Gap between top tube and bottom tube
    int minTubeOffset, maxTubeOffset;
    int numberOfTubes = 2;
    int distanceBetweenTubes;
    int[] tubeX = new int[numberOfTubes];
    int[] topTubeY = new int[numberOfTubes];
    Random random;
    int tubeVelocity = 8;


    public GameView(Context context) {
        super(context);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate(); //this will call onDraw();
            }
        };
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        toptube = BitmapFactory.decodeResource(getResources(), R.drawable.toptube);
        bottomtube = BitmapFactory.decodeResource(getResources(), R.drawable.obstacle);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth, dHeight);
        birds = new Bitmap[21];
        birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion1);
        birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion2);
        birds[2] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion3);
        birds[3] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion4);
        birds[4] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion5);
        birds[5] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion6);
        birds[6] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion7);
        birds[7] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion8);
        birds[8] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion9);
        birds[9] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion10);
        birds[10] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion11);
        birds[11] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion12);
        birds[12] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion13);
        birds[13] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion14);
        birds[14] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion15);
        birds[15] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion16);
        birds[16] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion17);
        birds[17] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion18);
        birds[18] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion19);
        birds[19] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion20);
        birds[20] = BitmapFactory.decodeResource(getResources(), R.drawable.runmotion21);



        birdX = dWidth/5 - birds[0].getWidth()/2; // Initially bird will be on centre
        birdY = dHeight/2 - birds[0].getHeight()/2;
        distanceBetweenTubes = dWidth*3/4;  //Our assumption
        minTubeOffset = gap/2;
        maxTubeOffset = dHeight - minTubeOffset - gap;
        random = new Random();
        for(int i=0; i<numberOfTubes; i++){
            tubeX[i] = dWidth + i*distanceBetweenTubes;
            topTubeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //We'll draw our view inside onDraw
        //Draw ths background on canvas
        //canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(background, null, rect, null);    //fixed
        if(birdFrame == 0){
            birdFrame = 1;
        }else if(birdFrame == 1){
            birdFrame = 2;
        }
        else if(birdFrame == 2){
            birdFrame = 3;
        }
        else if(birdFrame == 3){
            birdFrame = 4;
        }
        else if(birdFrame == 4){
            birdFrame = 5;
        }
        else if(birdFrame == 5){
            birdFrame = 6;
        }
        else if(birdFrame == 6){
            birdFrame = 7;
        }
        else if(birdFrame == 7){
            birdFrame = 8;
        }
        else if(birdFrame == 8){
            birdFrame = 9;
        }
        else if(birdFrame == 9){
            birdFrame = 10;
        }
        else if(birdFrame == 10){
            birdFrame = 11;
        }
        else if(birdFrame == 11){
            birdFrame = 12;
        }
        else if(birdFrame == 12){
            birdFrame = 13;
        }
        else if(birdFrame == 13){
            birdFrame = 14;
        }
        else if(birdFrame == 14){
            birdFrame = 15;
        }
        else if(birdFrame == 15){
            birdFrame = 16;
        }
        else if(birdFrame == 16){
            birdFrame = 17;
        }
        else if(birdFrame == 17){
            birdFrame = 18;
        }
        else if(birdFrame == 18){
            birdFrame = 19;
        }
        else if(birdFrame == 19){
            birdFrame = 20;
        }
        else {
            birdFrame = 0;
        }



        if(gameState) {
            //The bird should be on the screen
            if (birdY < dHeight - birds[0].getHeight() || velocity < 0) { //This way bird does not go beyond the bottom edge of the screen
                velocity += gravity; // As the bird falls, it gets faster and faster as the velocity value increments by gravity each time
                birdY += velocity;
            }
            for(int i=0; i<numberOfTubes; i++) {
                tubeX[i] -= tubeVelocity;
                if(tubeX[i] < -toptube.getWidth()){
                    tubeX[i] += numberOfTubes * distanceBetweenTubes;
                    topTubeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);
                }
//                canvas.drawBitmap(toptube, tubeX[i], topTubeY[i] - toptube.getHeight(), null);
                canvas.drawBitmap(bottomtube, tubeX[i], topTubeY[i] + gap, null);
            }
        }
        //We want the bird to be displayed at the centre of the screen
        //Both birds[0] and birds[1] have same dimension
        canvas.drawBitmap(birds[birdFrame], birdX, birdY, null);
        handler.postDelayed(runnable, UPDATE_MILLIS);
        Log.e("새 x값", String.valueOf(birdX));
        Log.e("새 y값: ", String.valueOf(birdY));
//        Log.e("장애물1 y값", String.valueOf(topTubeY[0]));
//        Log.e("장애물2 y값", String.valueOf(topTubeY[1]));
//        Log.e("장애물2 x값", String.valueOf(tubeX[1]));
//        Log.e("김동빈", "탑튜브y : "+ String.valueOf(topTubeY[0]));
//        Log.e("김동빈", "디스턴스 : "+String.valueOf(distanceBetweenTubes));
//        Log.e("김동빈", "버드y : "+String.valueOf(birdY));
//        Log.e("김동빈", "전체높이 : "+String.valueOf(dHeight));
//        Log.e("김동빈", "전체너비 : "+String.valueOf(dWidth));
        Log.e("김동빈", "튜브 : "+String.valueOf(tubeX[0]));
        /*if(tubeX[0] < 368 || tubeX[1] < 368){
            if(birdY > gap + topTubeY[0] || birdY >= gap + topTubeY[1]){

                Log.d("튜브이동","이동함");
            }
            tubeX[0] = tubeX[0] + 8;
            tubeX[1] = tubeX[1] + 8;
        }*/

        if(tubeX[0] < 368 && tubeX[0] > 168){
            if(birdY + 480 > gap + topTubeY[0]){

                Log.d("멈춤","1번 부딪힘");

                tubeX[0] = tubeX[0] + 8;
                tubeX[1] = tubeX[1] + 8;

            }

        }

        if(tubeX[1] < 368 && tubeX[1] > 168){

            if(birdY + 480 > gap + topTubeY[1]){

                Log.d("멈춤","2번 부딪힘");

                tubeX[0] = tubeX[0] + 8;
                tubeX[1] = tubeX[1] + 8;

            }
        }

    }
    //Get the touch event

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){ //That is tap is detected on screen
            //Here we want the bird to move upwards by some unit
            velocity = -30; //Let's say, 30 units on upward directions
            gameState = true;

    }

        return true;// By returning true indicates that we've done with touch event and no further action is required by Android
    }
}
