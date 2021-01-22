package com.example.mutithread1;

import lombok.Data;

import java.util.concurrent.*;

@Data
public class TestCallable implements Callable<Boolean> {
    private String url;
    private String name;

    public TestCallable(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downloader(url,name);
        System.out.println("下载了文件名为："+ name + "的文件");
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable testCallable = new TestCallable("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa2.att.hudong.com%2F27%2F81%2F01200000194677136358818023076.jpg&refer=http%3A%2F%2Fa2.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613394866&t=7e3364d04e5f695083739c26677d6b0b","图片1.jpg");
        TestCallable testCallable1 = new TestCallable("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi2.w.yun.hjfile.cn%2Fdoc%2F201303%2Fd5547c74-d9ad-4625-bd93-41c2817f1dff_03.jpg&refer=http%3A%2F%2Fi2.w.yun.hjfile.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613394939&t=22a52f7bc3b06fbce8fe9a05ca6e8aa7","图片2.jpg");
        TestCallable testCallable2 = new TestCallable("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3791918726,2864900975&fm=26&gp=0.jpg","图片3.jpg");

        //按照下面步骤启动线程
        //1、创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);

        //2、提交执行
        Future<Boolean> r1 = service.submit(testCallable);
        Future<Boolean> r2 = service.submit(testCallable1);
        Future<Boolean> r3 = service.submit(testCallable2);

        //3、获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        //4、关闭服务
        service.shutdown();
    }
}
