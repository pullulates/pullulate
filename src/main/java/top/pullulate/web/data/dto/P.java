package top.pullulate.web.data.dto;

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
    private String uuid;

    public static <T> P<T> success() {
        return p(HttpConstant.SUCCESS, HttpConstant.SUCCESS_MSG, null, null);
    }

    public static <T> P<T> success(T data) {
        return p(HttpConstant.SUCCESS, HttpConstant.SUCCESS_MSG, data, null);
    }

    public static <T> P<T> success(T data, String uuid) {
        return p(HttpConstant.SUCCESS, HttpConstant.SUCCESS_MSG, data, uuid);
    }

    public static <T> P<T> success(String msg) {
        return p(HttpConstant.SUCCESS, msg, null, null);
    }

    public static <T> P<T> error() {
        return p(HttpConstant.ERROR, HttpConstant.ERROR_MSG, null, null);
    }

    public static <T> P<T> error(T data) {
        return p(HttpConstant.ERROR, HttpConstant.ERROR_MSG, data, null);
    }

    public static <T> P<T> error(String msg) {
        return p(HttpConstant.ERROR, msg, null, null);
    }

    public static <T> P<T> error(String msg, T data) {
        return p(HttpConstant.ERROR, msg, data, null);
    }

    private static <T> P<T> p(int code, String msg, T data, String uuid) {
        P<T> p = new P<>();
        p.setCode(code);
        p.setMsg(MessageUtils.get(msg));
        p.setData(data);
        p.setUuid(uuid);
        return p;
    }
}
