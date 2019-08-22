package com.xuanyuan.makefun.codingstudy;

/**
 * 作者：罗发新
 * 时间：2019/8/15 0015    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class Animal implements IAnimal {
    public Animal(String name) {
        System.out.println("动物名字 " + name);
    }

    public Animal() {
        System.out.println("Animal普通构造函数 ");
    }

    public Animal(int age) {
        System.out.println("动物年龄 " + age);
    }

    public Animal(IAnimal iAnimal) {
        System.out.println("动物启动call方法");
    }

    public Animal(String name, int age) {
        System.out.println("动物年龄" + age + "  动物名字" + name);
    }

    private IAnimal iAnimal;

    @Override
    public void call() {
        System.out.println("运行了  Animal 中的call方法");
        if (iAnimal != null) {
            iAnimal.call();
        }
    }
}
