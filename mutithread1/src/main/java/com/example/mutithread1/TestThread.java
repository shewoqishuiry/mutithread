package com.example.mutithread1;

public class TestThread extends Thread {
    private String url;
    private String name;

    public TestThread(String url,String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downloader(url,name);
        System.out.println("下载了文件名为：" + name);
    }

    public static void main(String[] args){
        TestThread thread = new TestThread("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa2.att.hudong.com%2F27%2F81%2F01200000194677136358818023076.jpg&refer=http%3A%2F%2Fa2.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613394866&t=7e3364d04e5f695083739c26677d6b0b","图片1.jpg");
        TestThread thread1 = new TestThread("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi2.w.yun.hjfile.cn%2Fdoc%2F201303%2Fd5547c74-d9ad-4625-bd93-41c2817f1dff_03.jpg&refer=http%3A%2F%2Fi2.w.yun.hjfile.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613394939&t=22a52f7bc3b06fbce8fe9a05ca6e8aa7","图片2.jpg");
        TestThread thread2 = new TestThread("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3791918726,2864900975&fm=26&gp=0.jpg","图片3.jpg");

        thread.start();
        thread2.start();
        thread1.start();
    }

}
