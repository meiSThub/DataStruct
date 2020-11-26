package com.mei.test.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.imageio.stream.FileImageInputStream;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * RSA加密与解密算法
 *
 * @author mei
 *
 */
public class RSA {

    // 指定加密算法的名字
    public static String ALGORITHM = "RSA";
    // 指定key的位数 N的位数
    public static int KEYSIZE = 512;
    // 指定公钥存放的文件
    public static String PUBLIC_KEY_FILE = "public_key.dat";
    // 指定私钥存放的文件
    public static String PRIVATE_KEY_FILE = "private_key.dat";

    /**
     * 生成密钥对 公(e,n) 私(d,n)
     */
    public static void generateKeyPair() throws Exception {
        // 需要一个安全的随机数源
        SecureRandom sr = new SecureRandom();
        // 需要一个KeyPairGenerator对象
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        // 开始产生1和2步中用到的所有数据
        // 位数必须是64的倍数，在512--65536之间 默认1024
        kpg.initialize(KEYSIZE, sr);

        // 生成密钥对
        KeyPair keyPair = kpg.generateKeyPair();
        // 得到公钥
        Key publicKey = keyPair.getPublic();
        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        // 可以把公钥和私钥都写入文件保存下来
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(
                PUBLIC_KEY_FILE));
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(
                PRIVATE_KEY_FILE));

        oos1.writeObject(publicKey);
        oos2.writeObject(privateKey);

        oos1.close();
        oos2.close();
    }

    /**
     * 加密
     *
     * @param source
     * @return
     * @throws Exception
     */
    public static String encrypt(String source) throws Exception {
        generateKeyPair();
        // 取出公钥
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                PUBLIC_KEY_FILE));
        Key key = (Key) ois.readObject();
        ois.close();
        // 开始用公钥加密

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] b = source.getBytes();
        byte[] b1 = cipher.doFinal(b);
        // 用base64进行编码 字符和byte转换的一种方式
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);// 通过base64把字节数组转换成字符串
    }

    public static String decrypt(String cryptText) throws Exception {
        // 读文件
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                PRIVATE_KEY_FILE));

        Key key = (Key) ois.readObject();

        // 通过私钥解密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptText);// 先用base64把密文解密成字节流

        byte[] b = cipher.doFinal(b1);// 通过RSA把指定的字节流解密成明文
        return new String(b);
    }

    public static void main(String[] args) {
        try {
            // 客户端用公钥加密
            String str = "hello world!";
            String text = encrypt(str);
            System.out.println("密文:" + text);

            // 到了服务端进行用私钥解密
            String target = decrypt(text);
            System.out.println("明文:" + target);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
