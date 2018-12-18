package com.example.autocodetemplate.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class EncryUtil {
    private static final Logger logger = LoggerFactory.getLogger(EncryUtil.class);
    private static String strKey = "het11un";

    /**
     * MD5加密
     * @param plainText
     * @return
     */
    public static String md5(String plainText) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(plainText.getBytes("UTF-8"));
            byte s[] = m.digest();
            StringBuilder result = new StringBuilder();
            for(byte i : s){
                result.append(Integer.toHexString((0x000000FF&i)|0xFFFFFF00).substring(6));
            }
            return result.toString();
        } catch (Exception e) {
            logger.error("md5加密出错,{}",e.getMessage());
        }
        return null;
    }

    /**
     * 河豚加密
     * @param strClearText
     * @return
     * @throws Exception
     */
    public static String encryptBlowFish(String strClearText) {
     String strData="";

     try {
            SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
             Cipher cipher=Cipher.getInstance("Blowfish");
             cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
             byte[] encrypted=cipher.doFinal(strClearText.getBytes());
             strData = new String(Hex.encodeHex(encrypted));

             } catch (Exception e) {
             e.printStackTrace();
              }
             return strData;
     }

    /**
     * 河豚解密
     * @param strEncrypted
     * @return
     * @throws Exception
     */
     public static String decryptBlowFish(String strEncrypted) {
         String strData="";
         try {
             byte[] encrypted = Hex.decodeHex(strEncrypted.toCharArray());
             SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
             Cipher cipher=Cipher.getInstance("Blowfish");
             cipher.init(Cipher.DECRYPT_MODE, skeyspec);
             byte[] decrypted=cipher.doFinal(encrypted);
             strData=new String(decrypted);

         } catch (Exception e) {
             e.printStackTrace();
          }
         return strData;
    }

    public static void main(String[] args) {
        String ss = encryptBlowFish("yates");
        System.out.println(decryptBlowFish(ss));
    }
}
