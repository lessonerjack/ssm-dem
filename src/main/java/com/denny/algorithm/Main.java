package com.denny.algorithm;

/**
 * Created by denny.wang on 2018/8/13.
 */
public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
        encryptDecrypt.reset();

        do {
            encryptDecrypt.menu();
            System.out.print(">");
            String str = encryptDecrypt.in.next();
            if (str==null || str.isEmpty()) {
                System.out.println("输入错误,请重新输入!");
                continue;
            }
            char ch = str.charAt(0);
            switch(ch) {
                case 'e':
                case 'E':
                    if (!encryptDecrypt.encrypt()) {
                        System.out.println("加密失败,请重试!");
                    }
                    break;
                case 'd':
                case 'D':
                    if (!encryptDecrypt.decrypt()) {
                        System.out.println("解密失败,请重试!");
                    }
                    break;
                case 'p':
                case 'P':
                    encryptDecrypt.print();
                    break;
                case 'r':
                case 'R':
                    encryptDecrypt.reset();
                    break;
                case 'q':
                case 'Q':
                    encryptDecrypt.in.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (true);
    }
}
