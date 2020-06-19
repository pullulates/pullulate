package top.pullulate.common.enums;

/**
 * @功能描述: 菜单缓存枚举
 * @Author: pullulates
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public enum KeepAlive {

    ALIVE("0", "menu.keep.alive"), UNALIVE("1", "menu.unkeep.alive");

    private String code;

    private String label;

    KeepAlive(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean keepAlive(String code) {
        return ALIVE.code.equals(code);
    }

}
