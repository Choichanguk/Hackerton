package com.example.hackerton.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;

public class Player {
    private Drawable image = null;
    private Point point = new Point();
    private Point size = new Point();
    private Point delta;
    public Drawable getImage() {
        return image;
    }
    public void setImage(Drawable image) {
        this.image = image;
    }
    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }
    public Point getSize() {
        return size;
    }
    public void setSize(Point size) {
        this.size = size;
    }
    public void draw(Canvas canvas) {
        image.setBounds(point.x, point.y, point.x + size.x, point.y + size.y);
        image.draw(canvas);
    }
    public int getPointY() {
        int pointx=point.y;
        return pointx;
    }
    public void setDelta(int dx, int dy) {
        delta = new Point(dx, dy);
    }
    public void move(Rect surfaceFrame) {
        // X axis collision
        if (point.x + delta.x < 0 || point.x + delta.x + size.x > surfaceFrame.right)
            delta.x *= -1;
        else point.x += delta.x;
        // Y axis collision
        if (point.y + delta.y < 0 || point.y + delta.y + size.y > surfaceFrame.bottom)
            delta.y *= -1; else point.y += delta.y;
    }
    public void jump(Rect surfaceFrame, Context context) {
        int starty = point.y;
        //point.y=point.y-400;
        int height = 0;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        height = metrics.heightPixels;
        int jumpset = height*1/10;
        //Log.d("점프값","점프셋 값 :" + jumpset);

        for(int i = 10; i < jumpset; i+=10) {
            point.y -= i;
            try {
                Thread.sleep(20) ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }

//        for(int i = 10; i < jumpset; i+=10) {
//            point.y += i;
//            try {
//                Thread.sleep(20) ;
//            } catch (Exception e) {
//                e.printStackTrace() ;
//            }
//        }


    }

    // 중력 스레드
    public void downY(Rect surfaceFrame, Context context) {

        int height = 0;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        height = metrics.heightPixels;
        int jumpset = height*1/10;

        point.y = point.y + 20; //빠른 속도로 다운이 필요하면 + 값 추가



    }
}
