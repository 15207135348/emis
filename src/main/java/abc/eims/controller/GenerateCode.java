package abc.eims.controller;

import abc.eims.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author wangzhe
 * @date 2020/8/7 14:51
 */
@Slf4j
@RequestMapping(value = "/login")
@Controller
public class GenerateCode {
    @RequestMapping(value = "/code.do", method = RequestMethod.GET)
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        log.info("开始生成验证码");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        String captcha = WebUtil.genCaptcha(4);
        BufferedImage img = WebUtil.genCaptchaImg(captcha, 40, 150, 30);

        request.getSession().setAttribute("code", captcha);
        img.flush();
        try {
            ImageIO.write(img, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info("结束生成验证码");
    }
}
