package top.pullulate.core.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.core.user.UserInfo;
import top.pullulate.utils.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @功能描述: token工具类
 * @Author: pullulates
 * @Date: 2020/6/10 0010 13:43
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Component
@RequiredArgsConstructor
public class TokeUtils {

    private final RedisUtils redisUtils;

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(CacheConstant.CACHE_REQUEST_TOKEN_KEY);
        if (StrUtil.isNotBlank(token) && token.startsWith(CacheConstant.CACHE_TOKEN_PREFFIX)) {
            token = token.replace(CacheConstant.CACHE_TOKEN_PREFFIX, "");
        }
        return token;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public UserInfo getUserInfo(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StrUtil.isNotBlank(token)) {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(CacheConstant.CACHE_USER_INFO_PREFFIX);
            String userKey = getTokenKey(uuid);
            UserInfo userInfo = redisUtils.getCacheObject(userKey);
            return userInfo;
        }
        return null;
    }

    /**
     * 创建令牌
     *
     * @param userInfo 用户信息
     * @return 令牌
     */
    public String createToken(UserInfo userInfo) {
        String token = IdUtil.fastUUID();
        userInfo.setToken(token);
        setUserAgent(userInfo);
        refreshToken(userInfo);

        Map<String, Object> claims = new HashMap<>();
        claims.put(CacheConstant.CACHE_USER_INFO_PREFFIX, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param userInfo 用户信息
     *
     */
    public void verifyToken(UserInfo userInfo) {
        LocalDateTime expireTime = userInfo.getExpireTime();
        LocalDateTime currentTime = LocalDateTime.now();
        if (expireTime.minusMinutes(CacheConstant.CACHE_TOKEN_REFRESH_THRESHOLD_TIME).compareTo(currentTime) <= 0) {
            refreshToken(userInfo);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param userInfo 登录信息
     */
    public void refreshToken(UserInfo userInfo) {
        userInfo.setLoginTime(LocalDateTime.now());
        userInfo.setExpireTime(userInfo.getLoginTime().plusMinutes(CacheConstant.CACHE_TOKEN_EXPIRE_TIME));
        String userKey = getTokenKey(userInfo.getToken());
        redisUtils.setCacheObject(userKey, userInfo, CacheConstant.CACHE_TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, CacheConstant.CACHE_TOKEN_SECRET).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(CacheConstant.CACHE_TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private String getTokenKey(String uuid) {
        return CacheConstant.CACHE_USER_INFO_PREFFIX + uuid;
    }

    /**
     * 设置用户代理信息
     *
     * @param userInfo 用户信息
     */
    public void setUserAgent(UserInfo userInfo) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IPUtils.getIP(ServletUtils.getRequest());
        userInfo.setIp(ip);
        userInfo.setLocation(LocationUtils.getRealAddressByIP(ip));
        userInfo.setBrowser(userAgent.getBrowser().getName());
        userInfo.setOs(userAgent.getOperatingSystem().getName());
    }
}
