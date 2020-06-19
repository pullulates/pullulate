package top.pullulate.common.enums;

/**
 * @功能描述: 显示与隐藏枚举
 * @Author: pullulates
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public enum Show {

    SHOW("0", "show"), HIDE("1", "hidden");

    private String code;

    private String label;

    Show(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean show(String code) {
        return SHOW.code.equals(code);
    }

}
