package com.zygy.web.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt {

	// ��Կ
	private static Key key;
	// KEY����
	private static String KEY_STR = "2Na+Cl2=2NaCl";
	// ����
	public static final String UTF_8 = "UTF-8";
	public static final String DES = "DES";
	
	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";
	
	//��ȡ��
	public Key getSalt(int n){
		try {
			// KEY ������
		    KeyGenerator generator = KeyGenerator.getInstance(DES);
		    // ��ʼ��,��ȫ�������
		    generator.init(new SecureRandom((KEY_STR+"_"+getNRandomInt(n)).getBytes(UTF_8) ));
		    // ������Կ
		    key = generator.generateKey();
		    generator = null;
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
	    }
		return key;
	} 
	//��ȡn��10���ڵ������ַ���
	public String getNRandomInt(int n){
		Random random = new Random();
		String str = "";
		for (int i = 0; i < n; i++) {
			str+=random.nextInt(10);
		}
		return str;
	}
//	/**
//	 * ���ַ�������ɢ��, ֧��md5��sha1�㷨.
//	 */
//	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
//		try {
//			MessageDigest digest = MessageDigest.getInstance(algorithm);
//
//			if (salt != null) {
//				digest.update(salt);
//			}
//
//			byte[] result = digest.digest(input);
//
//			for (int i = 1; i < iterations; i++) {
//				digest.reset();
//				result = digest.digest(result);
//			}
//			return result;
//		} catch (GeneralSecurityException e) {
//			throw new RuntimeException(e);
//		}
//	}
	//md5����
	public String getMd5Encrypt(String pwd){
		
		return "";
	}
	
	
	//BASE64����
	public String getBASE64Encrypt(String pwd,Key key){
		try {
			// ���ݱ����ʽ��ȡ�ֽ�����
			byte[] sourceBytes = pwd.getBytes(UTF_8);
			// DES ����ģʽ
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// ���ܺ���ֽ�����
			byte[] encryptSourceBytes = cipher.doFinal(sourceBytes);
			// Base64������
			BASE64Encoder base64Encoder = new BASE64Encoder();
			return base64Encoder.encode(encryptSourceBytes);
		} catch (Exception e) {
			// throw Ҳ����һ�� return ·��
			throw new RuntimeException(e);
		}
	}
	
	//encode���ܽ���
	public String getEncode(String pwd){
		
		return "";
	}
	
	//��Դ���ӣ�http://www.jb51.net/article/49104.htm
	public static void main(String[] args) {
		Encrypt enc = new Encrypt();
		Key key = enc.getSalt(10);
		String enct = enc.getBASE64Encrypt("123456",key);
//		try {
////			byte[] byt = enc.digest("123456".getBytes("UTF-8"), enc.MD5, (KEY_STR+"_"+salt).getBytes("utf-8"), 10);
//			System.out.println();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println();
	}
}
