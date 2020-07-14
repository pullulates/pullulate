package top.pullulate.web.controller;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.common.constants.CaptchaConstant;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.web.data.dto.response.P;
import java.util.concurrent.TimeUnit;

/**
 * @功能描述:   验证码前端控制器
 * @Author: pullulates
 * @Date: 2020/6/10 0010 22:08
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final RedisUtils redisUtils;

    @GetMapping
    public P capthca() {
        String result, img;
        switch (CaptchaConstant.CURRENT_CAPTCHA_TYPE) {
            case CaptchaConstant.CAPTCHA_TYPE_PNG:
                SpecCaptcha specCaptcha = new SpecCaptcha(130, 48);
                result = specCaptcha.text();
                img = specCaptcha.toBase64();
                break;
            case CaptchaConstant.CAPTCHA_TYPE_GIF:
                GifCaptcha gifCaptcha = new GifCaptcha(130, 48);
                result = gifCaptcha.text();
                img = gifCaptcha.toBase64();
                break;
            case CaptchaConstant.CAPTCHA_TYPE_CHINESE:
                ChineseCaptcha chineseCaptcha = new ChineseCaptcha(130, 48);
                result = chineseCaptcha.text();
                img = chineseCaptcha.toBase64();
                break;
            case CaptchaConstant.CAPTCHA_TYPE_ARITHMETIC:
                ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(130, 48);
                result = arithmeticCaptcha.text();
                img = arithmeticCaptcha.toBase64();
                break;
            default:
                SpecCaptcha defaultCaptcha = new SpecCaptcha(130, 48);
                result = defaultCaptcha.text();
                img = defaultCaptcha.toBase64();
                break;
        }
        String uuid = IdUtil.fastSimpleUUID();
        redisUtils.setCacheObject(
                CacheConstant.CACHE_IMAGE_CAPTCHA_PREFFIX + uuid,
                result,
                CacheConstant.CACHE_IMAGE_CAPTCHA_EXPIRE_TIME,
                TimeUnit.MINUTES);
        return P.success(img, uuid);
    }
}
