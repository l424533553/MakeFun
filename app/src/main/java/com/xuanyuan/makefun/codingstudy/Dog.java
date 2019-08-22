package com.xuanyuan.makefun.codingstudy;

/**
 * 作者：罗发新
 * 时间：2019/8/15 0015    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class Dog extends Animal {
    @Override
    public void call() {
        super.call();
        System.out.println("在Dog中 调用了call 方法");
    }
}
