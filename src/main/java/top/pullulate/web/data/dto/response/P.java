package top.pullulate.web.data.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.pullulate.common.constants.HttpConstant;
import top.pullulate.utils.MessageUtils;

import java.io.Serializable;

/**
 * @功能描述:   返回数据封装
 * @Author: pullulates
 * @Date: 2020/6/10 0010 11:36
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@NoArgsConstructor
public class P<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String uuid;

    /**
     * 根据数量判定返回结果
     *
     * @param result    0-成功；1-失败
     */
    public static <T> P<T> p(int result) {
        return result > 0 ?
                p(HttpConstant.SUCCESS, HttpConstant.SUCCESS_MSG, null, null, null) :
                p(HttpConstant.ERROR, HttpConstant.ERROR_MSG, null, null, null);
    }

    /**
     * 只返回成功码和默认的成功消息
     */
    public static <T> P<T> success() {
        return p(HttpConstant.SUCCESS, HttpConstant.SUCCESS_MSG, null, null, null);
    }

    /**
     * 返回成功码、默认的成功消息和数据
     */
    public static <T> P<T> data(T data) {
        return p(HttpConstant.SUCCESS, HttpConstant.SUCCESS_MSG, data, null, null);
    }

    /**
     * 返回成功码、默认的成功消息和请求标识
     */
    public static <T> P<T> success(T data, String uuid) {
        return p(HttpConstant.SUCCESS, HttpConstant.SUCCESS_MSG, data, null, uuid);
    }

    /**
     * 返回成功码、默认的成功消息和会话凭证
     */
    public static <T> P<T> token(String token) {
        return p(HttpConstant.SUCCESS, HttpConstant.SUCCESS_MSG, null, token, null);
    }

    /**
     * 返回成功码、自定义成功消息
     */
    public static <T> P<T> success(String msg) {
        return p(HttpConstant.SUCCESS, msg, null, null, null);
    }

    /**
     * 返回失败码、默认的失败消息
     */
    public static <T> P<T> error() {
        return p(HttpConstant.ERROR, HttpConstant.ERROR_MSG, null, null, null);
    }

    /**
     * 返回失败码、默认的失败消息和数据
     */
    public static <T> P<T> error(T data) {
        return p(HttpConstant.ERROR, HttpConstant.ERROR_MSG, data, null, null);
    }

    /**
     * 返回失败码、自定义的失败消息
     */
    public static <T> P<T> error(String msg) {
        return p(HttpConstant.ERROR, msg, null, null, null);
    }

    /**
     * 返回失败码、自定义的失败消息和数据
     */
    public static <T> P<T> error(String msg, T data) {
        return p(HttpConstant.ERROR, msg, data, null, null);
    }

    /**
     * 统一的构造器
     * @param code  返回码
     * @param msg   返回消息
     * @param data  返回数据
     * @param token 会话凭证
     * @param uuid  请求标识
     * @param <T>   数据类型
     * @return
     */
    private static <T> P<T> p(int code, String msg, T data, String token, String uuid) {
        P<T> p = new P<>();
        p.setCode(code);
        p.setMsg(MessageUtils.get(msg));
        p.setData(data);
        p.setToken(token);
        p.setUuid(uuid);
        return p;
    }
}
