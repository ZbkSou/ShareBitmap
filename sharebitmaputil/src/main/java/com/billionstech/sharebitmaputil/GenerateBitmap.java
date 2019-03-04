package com.billionstech.sharebitmaputil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.billionstech.sharebitmaputil.bean.BitmapDispalyBean;
import com.billionstech.sharebitmaputil.bean.TextDisplayBean;

import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_PX;
import static com.billionstech.sharebitmaputil.util.BitmapUtil.decodeResource;

/**
 * User: bkzhou
 * Date: 2019/2/26
 * Description: 生成图片的最终类
 */
public class GenerateBitmap {

    public static Bitmap createImage(Activity context,  List<BitmapDispalyBean> bitmapDisplayBeanList, List<TextDisplayBean> textDisplayBeanList) throws Exception {

        Bitmap bgBitmap = null ;
        for(BitmapDispalyBean bean:bitmapDisplayBeanList){
            if(bean.isBackground()){
                bgBitmap = bean.getBitmap();
            }
        }
        if(bgBitmap==null){
            throw new Exception("no set background bitmap");
        }

        //保存背景尺寸
        int width = bgBitmap.getWidth();
        int height = bgBitmap.getHeight();
        //建立一个空的Bitmap
        Bitmap icon = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        // 初始化画布绘制的图像到icon上
        Canvas canvas = new Canvas(icon);
        // 绘制背景
        Paint photoPaint = new Paint();
        // 获取更清晰的图像采样，防抖动
        photoPaint.setDither(true);
        // 过滤一下，抗剧齿
        photoPaint.setFilterBitmap(true);

        Rect src = new Rect(0, 0, bgBitmap.getWidth(), bgBitmap.getHeight());
        Rect dst = new Rect(0, 0, width, height);
        canvas.drawBitmap(bgBitmap, src, dst, photoPaint);
        bgBitmap.recycle();

        //循环绘制图片
        if (bitmapDisplayBeanList != null) {
            for (BitmapDispalyBean bean : bitmapDisplayBeanList) {
                if(bean.isBackground()) {
                    continue;
                }
                if(bean.getRound()==0){

                    canvas.drawBitmap(bean.getBitmap(), bean.getX(), bean.getY(), photoPaint);
                }else {
                    int layerId = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
                    Rect rect = new Rect( bean.getX(),  bean.getY(),  bean.getX()+bean.getBitmap().getWidth(), bean.getY()+bean.getBitmap().getHeight());
                    final RectF rectF = new RectF(rect);
                    final float roundPx = bean.getRound();
                    final float roundPy = bean.getRound();
                    photoPaint.setAntiAlias(true);
                    photoPaint.setColor(Color.YELLOW);
                    canvas.drawRoundRect(rectF, roundPx, roundPy, photoPaint);
                    photoPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                    rect = new Rect( bean.getX(),  bean.getY(),  bean.getX()+bean.getBitmap().getWidth(), bean.getY()+bean.getBitmap().getHeight());
                    canvas.drawBitmap(bean.getBitmap(), null, rect, photoPaint);
                    photoPaint.setXfermode(null);
                    //将新图层画到canvas上
                    canvas.restoreToCount(layerId);
                }
            }
        }

        //循环绘制文字
        if (textDisplayBeanList != null) {
            LayoutInflater inflater = context.getLayoutInflater();

            for (TextDisplayBean bean : textDisplayBeanList) {
                View view = inflater.inflate(R.layout.layout_share_text, null);
                TextView textview = (TextView) view.findViewById(R.id.tv_share_text);
                textview.setText(bean.getText());
                textview.setTextColor(Color.BLACK);
                if (bean.getSize() != 0) {
                    textview.setTextSize(COMPLEX_UNIT_PX,bean.getSize());
                }
                if(bean.getWidth()!=0){
                    ViewGroup.LayoutParams params  = textview.getLayoutParams();
                    params.width = bean.getWidth();
                    textview.setLayoutParams(params);
                }

                if(bean.isMediate()){
                    textview.setGravity(Gravity.CENTER);
                    Bitmap textBitmap = getViewBitmapx(view);
                    canvas.drawBitmap(textBitmap, bean.getX()-(textBitmap.getWidth()/2), bean.getY()-(textBitmap.getHeight()/2), photoPaint);
                    textBitmap.recycle();
                }else {
                    textview.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                    Bitmap textBitmap = getViewBitmapx(view);
                    canvas.drawBitmap(textBitmap, bean.getX(), bean.getY(), photoPaint);
                    textBitmap.recycle();
                }
            }
        }

        //绘制完成
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return icon;
    }
    /**
     * 通过 view 转换 bitmap
     * @param addViewContent
     * @return
     */
    private static Bitmap getViewBitmapx(View addViewContent) {
        addViewContent.setDrawingCacheEnabled(true);
        addViewContent.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
            addViewContent.getMeasuredWidth(),
            addViewContent.getMeasuredHeight());

        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }
}
