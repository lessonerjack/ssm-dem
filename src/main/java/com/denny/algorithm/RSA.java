package com.denny.algorithm;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by denny.wang on 2018/8/13.
 */
public class RSA {
    public BigInteger n, e;// 公钥
    private BigInteger d;// 私钥
    private BigInteger p, q;
    private BigInteger eul;// n的欧拉函数

    /**
     * 函数功能:初始化RSA对象的相关信息
     * 参数含义:len表示大素数的二进制位数
     */
    public void init(int len) {
        Random random = new Random();
        // 产生大素数p和q
        p = BigInteger.probablePrime(len, random);
        q = BigInteger.probablePrime(len, random);
        // 计算出n
        n = p.multiply(q);
        // 计算出n的欧拉函数
        eul = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // 设置加解密指数e和d
        createExponent(eul);
    }

    /**
     * 函数功能:使用公钥进行加密
     * 参数含义:m表示要加密的明文
     */
    public BigInteger encryptByPublic(BigInteger m) {
        return m.modPow(e, n);
    }

    /**
     * 函数功能:使用私钥进行解密
     * 参数含义:c表示要解密的密文
     */
    public BigInteger decryptByPrivate(BigInteger c) {
        return c.modPow(d, n);
    }

    // 以下主要用于数字签名
    /**
     * 函数功能:使用私钥进行加密
     * 参数含义:m表示要加密的明文
     */
    public BigInteger encryptByPrivate(BigInteger m) {
        return decryptByPrivate(m);
    }

    /**
     * 函数功能:使用公钥进行解密
     * 参数含义:c表示要解密的密文
     */
    public BigInteger decryptByPublic(BigInteger c) {
        return encryptByPublic(c);
    }

    /**
     * 函数功能:从一个欧拉数中生成公钥、私钥指数
     * 参数含义:eul表示提供的欧拉数
     */
    private void createExponent(BigInteger eul) {
        // TODO Auto-generated method stub
        e = new BigInteger("3");
        d = e.modInverse(eul);
    }

    /**
     * 函数功能:输出RSA相关数据
     */
    public void print() {
        System.out.println("n: " + n);
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("e: " + e);
        System.out.println("d: " + d);
    }
}





