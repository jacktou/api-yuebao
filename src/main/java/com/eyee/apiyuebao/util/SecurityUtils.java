package com.eyee.apiyuebao.util;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * Description:
 * Author:jack
 * Date:下午9:02 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class SecurityUtils {

    public static final String STR_DES_ALGORITHM 	= "DES";
    public static final String STR_DES_ALGORITHM_3 	= "DESede/CBC/PKCS5Padding";

    public static final String STR_IV 	= "eyee@151";

    public SecurityUtils()
    {
    }

    @SuppressWarnings("static-access")
    public static void main(String args[])
    {
        SecurityUtils su = new SecurityUtils();
        try {
            String strKey = "1511@eyee";
            String strIv = "eyee@1511";

            //String strEN = su.strEncrypt(STR_DES_ALGORITHM_3, STR_IV, "5105105105105100", strKey);

            String strEN = "ZLoN4RyEPDPP8lmewvyex8suALXB2p2sFhXWVF1Dl5a%2Bf%2BDUeNlCVt3Tr%2BYMlSWiGc2jlRXbLm3Hma6TdvxTUcYM6U4pMc6TZFY5hkwB8xMZxKiAncFzFJe6pet4uHsrAPK0ouiuen%2BaxnEwcgrz2VpV2bcO1lRz";
            //strEN = URLDecoder.decode(strEN, "UTF8");

//            String key = md5("1511@eyee").substring(0, 24);
//            String iv = md5("eyee@1511").substring(0, 8);
//
//            System.out.println("====****** encrypt word ========= = " + strEN);
//            System.out.println("=====****** decrypt====== = " + su.strDecrypt(STR_DES_ALGORITHM_3, iv, strEN, key));


            System.out.println("=====****** decrypt222====== = " + SecurityUtils.decrypt(strEN, strKey, strIv));

            //strEN = su.strEncryptWithDES("5105105105105100", strKeyDES);
//            strEN = su.strEncryptWithDES("378282246310005", strKeyDES);
//            System.out.println("====-------- des encrypt word ========= = " + strEN);
//
//            strEN = "EUOoB7EYDuF7T/Ap8i+zZ/Aqgr57FbBT";
//            System.out.println("=====________des decrypt====== = " + su.strDecryptWithDES(strEN, strKeyDES));
//
//            System.out.println("MD5 = " + su.strMD5("111"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String encrypt, String key, String iv) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        encrypt = URLDecoder.decode(encrypt, "UTF8");
        key     = md5(key).substring(0, 24);
        iv      = md5(iv).substring(0, 8);

        Cipher cipher = Cipher.getInstance(STR_DES_ALGORITHM_3);
        cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(key.getBytes())), new IvParameterSpec(iv.getBytes()), new SecureRandom());

        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypt)));
    }


    /**
     * ECB module and NoPadding fill DES encrypt method.
     * @param str 			# encrypt string
     * @param key 		# encrypt key
     * @return encryptByte 	# cryptograph
     */
    public static String encryptDES(String str, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher myCipher = Cipher.getInstance(STR_DES_ALGORITHM);
        SecretKey sKey = new SecretKeySpec(key.getBytes(), "DES");
        myCipher.init(Cipher.ENCRYPT_MODE, sKey);

        return Base64.getEncoder().encodeToString(myCipher.doFinal(str.getBytes()));
    }

    /**
     * DES decrypt method.
     * @param encrypt 			# cryptograph
     * @param key 		# encrypt key
     * @return decryptByte 	# password
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static String decryptDES(String encrypt, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException
    {
        Cipher myCipher = Cipher.getInstance(STR_DES_ALGORITHM);
        SecretKey sKey = new SecretKeySpec(key.getBytes(), "DES");
        myCipher.init(Cipher.DECRYPT_MODE, sKey);

        return new String(myCipher.doFinal(Base64.getDecoder().decode(encrypt)));
    }


    public static String encrypt(String str, String algorithm, String iv, String key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret(new DESedeKeySpec(key.getBytes())), new IvParameterSpec(iv.getBytes()), new SecureRandom());

        return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes()));
    }

    public static String decrypt(String encrypt, String algorithm, String iv, String key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException
    {
        SecureRandom sr = new SecureRandom();
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);
        IvParameterSpec ivps = new IvParameterSpec(iv.getBytes());
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, securekey, ivps, sr);


        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypt)));
    }



    public static String md5(String enStr) throws NoSuchAlgorithmException {
        byte[] bytes = MessageDigest.getInstance("MD5").digest(enStr.getBytes());

        StringBuilder sb = new StringBuilder(32);
        String s;

        for (int i = 0; i < bytes.length; i++) {
            if (i % 32 == 0 && i != 0) {
                sb.append("\n");
            }
            s = Integer.toHexString(bytes[i]);
            if (s.length() < 2) {
                s = "0" + s;
            }
            if (s.length() > 2) {
                s = s.substring(s.length() - 2);
            }
            sb.append(s);
        }

        return sb.toString();
    }
}
