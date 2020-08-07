package abc.eims.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author wangzhe
 * @date 2020/8/7 15:20
 */
public class AESHelper {

    private Cipher ecipher;

    private Cipher dcipher;

    private static AESHelper aesHelper;

    /**
     * Input a string that will be md5 hashed to create the key.
     *
     * @return void, cipher initialized
     */
    public AESHelper() {
        try {
            SecretKeySpec skey = new SecretKeySpec("9f265d42ab3c66d8f50a3a2e793a30c2".getBytes(), "AES");
            this.setupCrypto(skey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AESHelper(String key) {
        SecretKeySpec skey = new SecretKeySpec(getMD5(key), "AES");
        this.setupCrypto(skey);
    }

    private void setupCrypto(SecretKey key) {
        // Create an 8-byte initialization vector
        byte[] iv = new byte[]
                {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        try {
            ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // CBC requires an initialization vector
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Input is a string to encrypt.
     * 加密
     *
     * @return a Hex string of the byte array
     */

    public String encrypt(String plaintext) {
        try {
            byte[] ciphertext = ecipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return byteToHex(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Input encrypted String represented in HEX
     * 解密
     *
     * @return a string decrypted in plain text
     */
    public String decrypt(String hexCipherText) {
        try {
            String plaintext = new String(dcipher.doFinal(hexToByte(hexCipherText)), StandardCharsets.UTF_8);
            return plaintext;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(byte[] ciphertext) {
        try {
            String plaintext = new String(dcipher.doFinal(ciphertext), StandardCharsets.UTF_8);
            return plaintext;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] getMD5(String input) {
        try {
            byte[] bytesOfMessage = input.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.update(("" + new Random().nextDouble()).getBytes()); // 加盐值，有问题
            return md.digest(bytesOfMessage);
        } catch (Exception e) {
            return null;
        }
    }

    private static final String HEXES = "0123456789ABCDEF";

    private static String byteToHex(byte[] raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    private static byte[] hexToByte(String hexString) {
        int len = hexString.length();
        byte[] ba = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            ba[i / 2] =
                    (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return ba;
    }

    public static AESHelper geneAesHelper() {
        if (null == aesHelper) {
            aesHelper = new AESHelper();
        }
        return aesHelper;
    }

}
