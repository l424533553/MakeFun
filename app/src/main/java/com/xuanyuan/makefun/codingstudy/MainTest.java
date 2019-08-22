package com.xuanyuan.makefun.codingstudy;

/**
 * 作者：罗发新
 * 时间：2019/8/16 0016    星期五
 * 邮件：424533553@qq.com
 * 说明：
 */
public class MainTest {
    public static void main(String[] args) {
        // 创建10个线程来调用【同一个】TestForSynchronized实例(对象)
        MyTest temp = new MyTest();
        for (int index = 0; index < 10; index++) {
            MyThread thread = new MyThread(temp);
            thread.start();
        }
    }
}

class MyThread extends Thread {
    private MyTest testObject;
    public MyThread(MyTest testObject) {
        this.testObject = testObject;
    }
    @Override
    public void run() {
        System.out.println(" 线程名" + Thread.currentThread().getName() + "- **************   " + testObject.commonPrint());
        System.out.println(" 线程名" + Thread.currentThread().getName() + "- ##############   " + testObject.print1());
    }
}

class MyTest {
    public int count = 0;
    //print1 方法   -synchronized块(对象级，这里是对象不是地址引用)
    public int print1() {
        synchronized (this) {
            count++;
        }
        return count;
    }

    //print2 方法  -synchronized块(类级别)
    public int print2() {
        synchronized (com.xuanyuan.makefun.codingstudy.Test.class) {
            count++;
        }
        return count;
    }

    //print3 方法  -synchronized 方法
    public synchronized int print3() {
        count++;
        return count;
    }

    //普通方法
    public int commonPrint() {
        return count;
    }
}


 class TestSynchronized   {
    //定义一个静态对象
    private static Object mutex = new Object();
    public void Test() {
        synchronized (mutex) {
            //涉及并发问题的代码块
        }
    }

     public synchronized void m () {
     }
     public  void m1 () {
        synchronized (this){
         }
     }

 }

/**
 * 测试线程
 */
class TestThread extends Thread {
    @Override
    public void run() {
        //仅仅只是继承Thread,不实现Runnable。其中的super.run()方法可以省略去掉，里面的代码不会运行
        super.run();
        try {

            System.out.println("开始wait  " + System.currentTimeMillis());
            synchronized (this) {
                this.wait(30000);
            }
            System.out.println("wait 执行过了");

            while (true) {
                System.out.println("数据 打印");
                sleep(3000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





