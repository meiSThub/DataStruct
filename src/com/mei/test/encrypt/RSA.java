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
 * RSA����������㷨
 * 
 * @author mei
 *
 */
public class RSA {

	// ָ�������㷨������
	public static String ALGORITHM = "RSA";
	// ָ��key��λ�� N��λ��
	public static int KEYSIZE = 512;
	// ָ����Կ��ŵ��ļ�
	public static String PUBLIC_KEY_FILE = "public_key.dat";
	// ָ��˽Կ��ŵ��ļ�
	public static String PRIVATE_KEY_FILE = "private_key.dat";

	/**
	 * ������Կ�� ��(e,n) ˽(d,n)
	 */
	public static void generateKeyPair() throws Exception {
		// ��Ҫһ����ȫ�������Դ
		SecureRandom sr = new SecureRandom();
		// ��Ҫһ��KeyPairGenerator����
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		// ��ʼ����1��2�����õ�����������
		// λ��������64�ı�������512--65536֮�� Ĭ��1024
		kpg.initialize(KEYSIZE, sr);

		// ������Կ��
		KeyPair keyPair = kpg.generateKeyPair();
		// �õ���Կ
		Key publicKey = keyPair.getPublic();
		// �õ�˽Կ
		Key privateKey = keyPair.getPrivate();
		// ���԰ѹ�Կ��˽Կ��д���ļ���������
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
	 * ����
	 * 
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String source) throws Exception {
		generateKeyPair();
		// ȡ����Կ
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				PUBLIC_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();
		// ��ʼ�ù�Կ����

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] b = source.getBytes();
		byte[] b1 = cipher.doFinal(b);
		// ��base64���б��� �ַ���byteת����һ�ַ�ʽ
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(b1);// ͨ��base64���ֽ�����ת�����ַ���
	}

	public static String decrypt(String cryptText) throws Exception {
		// ���ļ�
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				PRIVATE_KEY_FILE));

		Key key = (Key) ois.readObject();

		// ͨ��˽Կ����
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b1 = decoder.decodeBuffer(cryptText);// ����base64�����Ľ��ܳ��ֽ���

		byte[] b = cipher.doFinal(b1);// ͨ��RSA��ָ�����ֽ������ܳ�����
		return new String(b);
	}

	public static void main(String[] args) {
		try {
			// �ͻ����ù�Կ����
			String str = "hello world!";
			String text = encrypt(str);
			System.out.println("����:" + text);

			// ���˷���˽�����˽Կ����
			String target = decrypt(text);
			System.out.println("����:" + target);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
