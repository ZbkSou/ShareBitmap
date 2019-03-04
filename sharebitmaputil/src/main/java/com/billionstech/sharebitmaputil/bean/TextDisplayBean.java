package com.billionstech.sharebitmaputil.bean;

import android.text.Html;
import android.text.Spanned;

/**
 * User: bkzhou
 * Date: 2019/2/26
 * Description:
 */
public class TextDisplayBean {
    //文字
    private Spanned text;
    //w位置
    private int x;
    private int y;
    //文字尺寸
    private int size;
    //居中
    private boolean isMediate;
    //行宽，换行
    private int width;


    public TextDisplayBean(Spanned text, int x, int y, int size) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.width =0;
        this.isMediate =false;
    }



    public TextDisplayBean(String text, int x, int y, int size) {
        this.text = Html.fromHtml(text);
        this.x = x;
        this.y = y;
        this.size = size;
        this.width =0;
        this.isMediate =false;
    }

    public boolean isMediate() {
        return isMediate;
    }

    public void setMediate(boolean mediate) {
        this.isMediate = mediate;
    }



    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public Spanned getText() {
        return text;
    }

    public void setText(String text) {
        this.text = Html.fromHtml(text);
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
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
