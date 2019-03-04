package com.billionstech.sharebitmap;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.billionstech.sharebitmaputil.GenerateBitmap;
import com.billionstech.sharebitmaputil.bean.BitmapDispalyBean;
import com.billionstech.sharebitmaputil.bean.TextDisplayBean;
import com.billionstech.sharebitmaputil.util.BitmapUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.CropTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MainActivity extends AppCompatActivity {

    private ImageView ivShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivShare = this.findViewById(R.id.iv_share);
        getImage();
    }
    private void getImage(){
        //文字内容
        List<TextDisplayBean> textDisplayBeanList = new ArrayList<>();
        //图片内容
        List<BitmapDispalyBean> bitmapDispalyBeanList = new ArrayList<>();

        //简单的设置文字颜色和外置
        TextDisplayBean bean1 = new TextDisplayBean("<font color=\"#E42011\">标题</font>", 192, 50, 58);
        textDisplayBeanList.add(bean1);
        //设置居中之后xy 标记位置中间
        TextDisplayBean bean2 = new TextDisplayBean("<font color=\"#F7CC06\">居中的文字，xy 标记文字中间</font>", 630, 100, 40);
        bean2.setMediate(true);
        textDisplayBeanList.add(bean2);
        //设置宽度之后文字自动换行
        TextDisplayBean bean3 = new TextDisplayBean("<font color=\"#E42011\">展示换行问 is的方式都得发送的发送的发送的发送的发送的的发送的说的 都是说的 发送的是东方闪电说的胜多负少的水电费</font>", 92, 50, 30);
        bean3.setWidth(100);
        textDisplayBeanList.add(bean3);


        //设置背景图片
        Bitmap image = BitmapUtil.decodeResource(this.getResources(),R.drawable.bg_share_content5);
        BitmapDispalyBean imgbean1 = new BitmapDispalyBean(image, 0, 0, 0,true);
        bitmapDispalyBeanList.add(imgbean1);

        //添加图片
        Bitmap image2 = BitmapUtil.decodeResource(this.getResources(),R.drawable.delete_tag);
        BitmapDispalyBean imgbean2 = new BitmapDispalyBean(image2, 0, 100, 0);
        bitmapDispalyBeanList.add(imgbean2);
        //设置图片圆角解决添加圆角头像
        Bitmap image4 = BitmapUtil.decodeResource(this.getResources(),R.drawable.publc_number_code_img);
        BitmapDispalyBean imgbean4 = new BitmapDispalyBean(image4, 600, 400, 100);
        bitmapDispalyBeanList.add(imgbean4);
        try {
            Bitmap bitmap = GenerateBitmap.createImage(this,bitmapDispalyBeanList,textDisplayBeanList);
            ivShare.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
