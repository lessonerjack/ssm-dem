package com.denny.algorithm;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by denny.wang on 2018/8/13.
 */
public class EncryptDecrypt {

    private RSA rsa = new RSA();
    public Scanner in = new Scanner(System.in);

    /**
     * 函数功能:菜单显示
     */
    public void menu() {
        System.out.println("**********Welcome to use RSA encoder**********");
        System.out.println("               e: encrypt 加密               ");
        System.out.println("               d: decrypt 解密               ");
        System.out.println("               p: print   显示               ");
        System.out.println("               r: reset   重置               ");
        System.out.println("               q: quit    退出               ");
        System.out.println("input your choice:");
    }

    /**
     * 函数功能:加密运算
     */
    public boolean encrypt() {
        System.out.println("输入10进制数据:");
        System.out.print(">");
        String str = in.next();
        if (str==null || str.isEmpty() || !islegal(str))
            return false;
        BigInteger m = new BigInteger(str);
        long start = System.currentTimeMillis();
        BigInteger c = rsa.encryptByPublic(m);
        long finish = System.currentTimeMillis();
        System.out.println("用时:" + (finish-start) + "ms.");
        System.out.println("明文: " + m);
        System.out.println("密文: " + c);
        return true;
    }

    /**
     * 函数功能:解密运算
     */
    public boolean decrypt() {
        System.out.println("输入10进制数据:");
        System.out.print(">");
        String str = in.next();
        if (str==null || str.isEmpty() || !islegal(str))
            return false;
        BigInteger c = new BigInteger(str);
        long start = System.currentTimeMillis();
        BigInteger m = rsa.decryptByPrivate(c);
        long finish = System.currentTimeMillis();
        System.out.println("用时:" + (finish-start) + "ms.");
        System.out.println("密文: " + c);
        System.out.println("明文: " + m);
        return true;
    }

    /**
     * 函数功能:输出RSA相关信息
     */
    public void print() {
        rsa.print();
    }

    /**
     * 函数功能:重置RSA相关信息
     */
    public void reset() {
        System.out.print("输入密钥长度: ");
        int len = in.nextInt();
        load(len>>1);
    }

    /**
     * 函数功能:根据给定位数len加载rsa
     */
    private void load(int len) {
        // TODO Auto-generated method stub
        System.out.println("初始化...");
        long start = System.currentTimeMillis();
        rsa.init(len);
        long finish = System.currentTimeMillis();
        System.out.println("初始化完成.");
        System.out.println("用时:" + (finish-start) + "ms.");
    }

    /**
     * 函数功能:判断输入字符串str是否合法
     * 参数含义:str代表输入的字符串
     */
    private boolean islegal(String str) {
        // TODO Auto-generated method stub
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}

