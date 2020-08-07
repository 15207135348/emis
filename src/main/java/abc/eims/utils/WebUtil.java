package abc.eims.utils;

import sun.font.FontDesignMetrics;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wangzhe
 * @date 2020/8/7 14:55
 */
public class WebUtil {
    private static final char[] chars = "0123456789QWERTYUPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm".toCharArray();

    public static boolean isAjaxRequest(HttpServletRequest request) {
        boolean result = false;
        String headerX = request.getHeader("X-Requested-With");
        return headerX != null && headerX.equalsIgnoreCase("XMLHttpRequest");

    }

    /**
     * 生成一个位数为count的随机验证码
     *
     * @param count
     * @return
     */
    public static String genCaptcha(int count) {
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < count; i++) {
            char c = chars[ThreadLocalRandom.current().nextInt(chars.length)];//随机选取一个字母或数字
            captcha.append(c);
        }
        return captcha.toString();
    }

    /**
     * 为一个验证码生成一个图片
     * <p>
     * 特性：
     * - 颜色随机
     * - 上下位置随机
     * - 左右位置随机，但字符之间不会重叠
     * - 左右随机旋转一个角度
     * - 避免字符出界
     * - 随机颜色的小字符做背景干扰
     * - 根据字符大小自动调整图片大小、自动计算干扰字符的个数
     *
     * @param captcha
     * @return
     */
    public static BufferedImage genCaptchaImg(String captcha, int height, int width, int fontSize) {
        ThreadLocalRandom r = ThreadLocalRandom.current();

        int count = captcha.length();
        int fontMargin = (width - count * fontSize) / (1 + count); //字符间隔

        //背景颜色
        Color bkColor = Color.WHITE;
        //验证码的颜色
        Color[] catchaColor = {Color.MAGENTA, Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE, Color.PINK};

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        //填充底色为灰白
        g.setColor(bkColor);
        g.fillRect(0, 0, width, height);

        //画边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        //画干扰字母、数字
        int dSize = fontSize / 3; //调整分母大小以调整干扰字符大小
        Font font = new Font("Fixedsys", Font.PLAIN, dSize);
        g.setFont(font);
        int dNumber = width * height / dSize / dSize / 2;//根据面积计算干扰字母的个数
        for (int i = 0; i < dNumber; i++) {
            char d_code = chars[r.nextInt(chars.length)];
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawString(String.valueOf(d_code), r.nextInt(width), r.nextInt(height));
        }
        //开始画验证码，设置验证码字体
        font = new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, fontSize);
        g.setFont(font);
        //获取字体基线上下高度
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        for (int i = 0; i < count; i++) {
            char c = captcha.charAt(i);
            g.setColor(catchaColor[r.nextInt(catchaColor.length)]);//随机选取一种颜色
            int x = fontMargin * (i + 1) + fontSize * i + r.nextInt(-fontMargin / 2, fontMargin / 2); //横向偏移的距离
            int y = metrics.getAscent() + r.nextInt(height - fontSize - 5); //上下偏移的距离
            g.drawString(String.valueOf(c), x, y);
        }
        g.dispose();
        return image;
    }
}