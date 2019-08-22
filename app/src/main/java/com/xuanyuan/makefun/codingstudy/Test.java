package com.xuanyuan.makefun.codingstudy;

/**
 * 作者：罗发新
 * 时间：2019/8/15 0015    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class Test {
    public static void main(String[] args) {

        String username="j";
        System.out.println( "数据=="+    username.trim() );
        System.out.println( "数据=="+    username.isEmpty() );
        System.out.println( "数据=="+    !username.trim().isEmpty() );


    }
    private  int  count;
    private void test22(){
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    private void test() {
//            TestThread thread1 = new TestThread();
//            thread1.start();
//            TestThread thread2 = new TestThread();
//            thread2.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("运行了 Runnable 中的方法");
//            }
//        }) {
//            @Override
//            public void run() {
//                super.run();
//                System.out.println("运行了 Thread   中的方法");
//            }
//        }.start();

//        Thread thread = new TestThread();
//        thread.start();
//        new Thread(new TestRunnable()).start();
//        final Thread thread = new TestThread();
//        thread.start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                    synchronized (thread){
//                        thread.notify();
//                    }
//
//                    // 使用情况  ，和测试情况。
//                    System.out.println("唤醒 thread线程 "+System.currentTimeMillis());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    /**
     * 不用等待 测试
     * @param t1  线程
     */
    private void unwait(Thread t1) {
        synchronized (t1) {
            t1.notify();
            // 启动“线程t1” System.out.println(Thread.currentThread().getName()+" start t1"); t1.start();
            // 主线程等待t1通过notify()唤醒。 System.out.println(Thread.currentThread().getName()+" wait()"); t1.wait();
            // 不是使t1线程等待，而是当前执行wait的线程等待 System.out.println(Thread.currentThread().getName()+" continue");
        }
    }

    /**
     * 使用测试，  使用测试
     */
    private class TestRunnable implements Runnable {
        @Override
        public void run() {

        }
    }

    //

}
