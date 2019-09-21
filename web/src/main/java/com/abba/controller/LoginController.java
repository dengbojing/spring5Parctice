package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.LoginParam;
import com.abba.model.dto.UserDTO;
import com.abba.service.ILoginService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final ILoginService<UserDTO> loginService;
    private final DefaultKaptcha captchaProducer;

    public LoginController(ILoginService<UserDTO> userService, DefaultKaptcha captchaProducer) {
        this.loginService = userService;
        this.captchaProducer = captchaProducer;
    }

    /**
     * 登录
     * @param loginParam 登录参数
     * @return 统一返回格式 {@link BaseResponse}; 返回jwt
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> login(@RequestBody LoginParam loginParam){
        return BaseResponse.<String>builder().build().success("登录成功",loginService.login(loginParam).orElseGet(String::new));

    }

    /**
     * 生成验证码
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cipher",method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> defaultKaptcha(HttpServletRequest httpServletRequest) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        //生产验证码字符串并保存到session中
        String createText = captchaProducer.createText();
        httpServletRequest.getSession().setAttribute("verifyCode", createText);
        //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage challenge = captchaProducer.createImage(createText);
        ImageIO.write(challenge, "jpg", jpegOutputStream);
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
        return bodyBuilder.body(captchaChallengeAsJpeg);
    }


    /**
     * 验证的方法
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/cipher/verify")
    public BaseResponse<String> cipherVerify(HttpServletRequest httpServletRequest){
        String captchaId = (String) httpServletRequest.getSession().getAttribute("verifyCode");
        String parameter = httpServletRequest.getParameter("vrifyCode");
        return BaseResponse.<String>builder().build().adaptive(s -> s.equalsIgnoreCase(parameter),captchaId);
    }
}
