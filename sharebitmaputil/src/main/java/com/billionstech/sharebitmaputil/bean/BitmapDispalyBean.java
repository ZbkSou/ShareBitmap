package com.billionstech.sharebitmaputil.bean;

import android.graphics.Bitmap;

/**
 * User: bkzhou
 * Date: 2019/2/26
 * Description:
 */
public class BitmapDispalyBean {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int round;


    private boolean isBackground;

    public BitmapDispalyBean(Bitmap bitmap, int x, int y, int round) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.round = round;
        this.isBackground = false;
    }

    public BitmapDispalyBean(Bitmap bitmap, int x, int y, int round,boolean b) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.round = round;
        this.isBackground = b;
    }
    public boolean isBackground() {
        return isBackground;
    }

    public void setBackground(boolean background) {
        isBackground = background;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
