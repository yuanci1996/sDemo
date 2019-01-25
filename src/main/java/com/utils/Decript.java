package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Decript {
	  
	 public static String SHA1(String decript) {
	 try {
	  MessageDigest digest = MessageDigest
	   .getInstance("SHA-1");
	  digest.update(decript.getBytes());
	  byte messageDigest[] = digest.digest();
	  // Create Hex String
	  StringBuffer hexString = new StringBuffer();
	  // �ֽ�����ת��Ϊ ʮ������ ��
	  for (int i = 0; i < messageDigest.length; i++) {
	  String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
	  if (shaHex.length() < 2) {
	   hexString.append(0);
	  }
	  hexString.append(shaHex);
	  }
	  return hexString.toString();
	  
	 } catch (NoSuchAlgorithmException e) {
	  e.printStackTrace();
	 }
	 return "";
	 }
	}
