package abc.eims.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangzhe
 * @date 2020/8/10 8:46
 */
public class CookieUtil {

    private static final String COOKIE_NAME = "abc.eims";

    /**
     * cookie生成算法
     *
     * @param cookieValue 用户的标志
     */
    private static String genCookieValue(String cookieValue) {
        return Base64s.encode(cookieValue);
    }

    /**
     * 设置浏览器cookie，方便以后可以无密码登陆
     */
    public static void addCookie(String cookieValue) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String value = genCookieValue(cookieValue);
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setMaxAge(3600 * 24 * 30);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 用户注销后，要清除cookie
     */
    public static void removeCookie() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * 从cookie中解析出用户名
     */
    public static String getCookieValueFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Cookie[] cookies = request.getCookies();
        String encodedText = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    encodedText = cookie.getValue();
                    break;
                }
            }
        }
        if (encodedText == null) {
            return null;
        }
        return Base64s.decode(encodedText);
    }

}
