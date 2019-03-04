# ShareBitmap
由于项目分享图片样式经常变动，图片内容还包含用户昵称，推荐码等信息。在服务器上生服务器压力过大。所以做了这样的 module用来生成分享用图片。

考虑到实际项目中基本上都会使用自己的二维码生成工具，图片请求框架，为了不加载多余的代码，也是为了更灵活的接受多个来源的图片，只接收 Bitmap的图片传入。

用法简单，但是要考虑到跟后台通信还需要根据自己项目写一些代码。
```
    //文字内容
        List<TextDisplayBean> textDisplayBeanList = new ArrayList<>();
        //图片内容
        List<BitmapDispalyBean> bitmapDispalyBeanList = new ArrayList<>();

        //简单的设置文字颜色和外置，不是用 font 标签的话默认是黑色
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
            //通过 Imageview显示出来
            ivShare.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
```