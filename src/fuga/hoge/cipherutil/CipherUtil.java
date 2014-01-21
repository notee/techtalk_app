package fuga.hoge.cipherutil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtil {

    private static String _KEY = "WhatWeWantToKnow";

    private static String _ALGORITHM = "AES";
    private static String _MODE = "CBC";
    private static String _PADDING = "PKCS5Padding";

    private static String _IV = "0123456789abcdeffedcba9876543210";


    public static String encipher(String plainText) throws Exception {
        if (plainText.equals("")) {
            return plainText;
        }

        SecretKeySpec secKey = new SecretKeySpec( _KEY.getBytes(), _ALGORITHM );
        IvParameterSpec iv	= new IvParameterSpec( hexToByte(_IV) );

        Cipher cipher = Cipher.getInstance(_ALGORITHM+"/"+_MODE+"/"+_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secKey, iv);

        byte[] byte_data = plainText.getBytes();

        byte[] enciphered_data = cipher.doFinal(byte_data);

        return byteToHex(enciphered_data);

    }

    private static String byteToHex(byte[] byteArray) {
        //バイト配列を16進数文字列になおす
        StringBuilder sb = new StringBuilder();

        int int_byte;

        for ( byte b : byteArray ){
            //上位の符号ビットを除去するため下位8bitのみを確認している
            int_byte = 0xff & (int)b;
            if( int_byte < 0x10 ){
                sb.append("0");
            }
            sb.append(Integer.toHexString(int_byte));
        }

        return sb.toString();
    }

    private static byte[] hexToByte(String hex_str) {

        //16進数文字列をバイト配列になおす
        byte[] byteArray = new byte[ hex_str.length() / 2 ];

        for (int index = 0; index < byteArray.length; index++) {
            byteArray[index] = (byte) Integer.parseInt( hex_str.substring( index * 2, ( index + 1 ) * 2 ), 16 );
        }

        return byteArray;
    }
}
