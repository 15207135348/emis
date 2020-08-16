package abc.eims.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64加密
 *
 * @author wangzhe
 * @date 2020/8/10 8:46
 */
public class Base64s {

    /**
     * 加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String encode(String text) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = text.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(textByte);
    }

    /**
     * 解密
     *
     * @param encodedText 密文
     * @return 明文
     */
    public static String decode(String encodedText) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedText), StandardCharsets.UTF_8);
    }

}
