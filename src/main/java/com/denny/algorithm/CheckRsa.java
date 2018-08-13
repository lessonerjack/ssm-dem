package com.denny.algorithm;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * Created by denny.wang on 2018/8/13.
 */
public class CheckRsa {
    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static BigInteger modulus;
    private static KeyFactory keyFactory;
    private static Cipher cipher;

    public static void init(String pubKey) throws Exception {
        keyFactory = KeyFactory.getInstance("RSA");
        cipher = Cipher.getInstance("RSA");
        modulus = new BigInteger(pubKey,16);
    }

    public static byte[] encrypt(String originData) throws Exception {

        //公钥加密
        BigInteger publicExponent = new BigInteger("3");
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus,publicExponent);
        PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);

        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = originData.getBytes("UTF-8");
        int byteLength = bytes.length;
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (byteLength - offSet > 0) {
            if (byteLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(bytes, offSet, byteLength - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData=out.toByteArray();
        return encryptedData;
    }

    public static String decrypt(byte[] encryptedData,String priKey) throws Exception {
        //私钥解密
        BigInteger privateExponent = new BigInteger(priKey,16);
        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus,privateExponent);
        PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        int offSet1 = 0;
        byte[] cache1;
        int i1 = 0;
        // 对数据分段解密
        while (inputLen - offSet1 > 0) {
            if (inputLen - offSet1 > MAX_DECRYPT_BLOCK) {
                cache1 = cipher.doFinal(encryptedData, offSet1, MAX_DECRYPT_BLOCK);
            } else {
                cache1 = cipher.doFinal(encryptedData, offSet1, inputLen - offSet1);
            }
            out1.write(cache1, 0, cache1.length);
            i1++;
            offSet1 = i1 * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out1.toByteArray();
        System.out.println("decryptData:" + new String(decryptedData));
        return new String(decryptedData);
    }
    public static void main(String[] args) throws Exception {

/*        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair pair = generator.generateKeyPair();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) pair.getPrivate();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) pair.getPublic();
        System.out.println("publicKey exponent:" + rsaPublicKey.getPublicExponent());
        System.out.println("publicKey modules:" + rsaPublicKey.getModulus());
        System.out.println("publicKey format:" + rsaPublicKey.getFormat());
        System.out.println("publicKey encode:" + encryptBASE64(rsaPublicKey.getEncoded()));
        System.out.println("---------------------华丽的分割线-------------------------");
        System.out.println("privateKey exponent:" + rsaPrivateKey.getPrivateExponent());
        System.out.println("privateKey modules:" +  rsaPrivateKey.getModulus());
        System.out.println("privateKey format:" + rsaPrivateKey.getFormat());*/
        String pub = "B02D8945C76E0E5DDB120C8A2352F340F09FFB64053AE8281924FD5C1EB8838CCEC30D4A16B1D8BD86FAB824420DFB055C81DA8EEDBDA65113F9DAB6044765D8499CF643EE7EB776EC4A936D9695073D4BF6BE93322ED7389C27DCDE9F8BC2954CFC7A9C3E2579B69A9DBD7C363F95EA53EEA204035B1B5F59598D65FD69DB11";
        String pri = "7573B0D92F9EB43E920C085C178CA22B4B155242AE27457010C35392BF25AD0889D75E316476907E59FC7AC2D6B3FCAE3DABE709F3D3C4360D513C79582F9939C093929082EEE72B125B51B363D217EEE84656C6E4B3C9636D62B00035A29C944F06E513CDC52DE8E8DF587F5F17ABE2C86AC2ABB877F8FB14EB30E89D34B543";
        String originData="oooooo年后弓弩ID恢复我会后覅好的覅会更好";
        init(pub);
        byte[] encrypt = encrypt(originData);
        String decrypt = decrypt(encrypt,pri);
        System.out.println("----------------------------------");




    }
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static void get(String hex){
        Integer x = Integer.parseInt(hex,16);
        System.out.println(x);
    }

    public  static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
