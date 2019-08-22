package com.xuanyuan.makefun.codingstudy;

/**
 * 作者：罗发新
 * 时间：2019/8/16 0016    星期五
 * 邮件：424533553@qq.com
 * 说明：
 */

import java.util.concurrent.TimeUnit;

/*
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生藏读现象
 */
public class Account {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        final Account a = new Account();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.set("xiaoming", 100);
            }
        }).start();

        System.out.println("睡眠前时间  =" + System.currentTimeMillis());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("睡眠后时间  =" + System.currentTimeMillis());
        System.out.println(a.getBalance());
        System.out.println("获取打印时间  =" + System.currentTimeMillis());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance());

        System.out.println("获取最后的打印时间  =" + System.currentTimeMillis());

//        synchronized (){
//
//        }

    }


    /**
     * 测试
     */
    private void test() {

    }


    public class Test {
        private int count = 0;

        //print1 方法   -synchronized块(对象级，这里是对象不是地址引用)
        public void print1() {
            synchronized (this) {
                count++;
                System.out.println("数据==" + count);
            }
        }

        //print2 方法  -synchronized块(类级别)
        public void print2() {
            synchronized (com.xuanyuan.makefun.codingstudy.Test.class) {
                count++;
                System.out.println("数据==" + count);
            }
        }

        //print3 方法  -synchronized 方法
        public synchronized void print3() {
            count++;
            System.out.println("数据==" + count);
        }

        //普通方法
        public void commonPrint() {
            System.out.println("数据==" + count);
        }
    }


}



