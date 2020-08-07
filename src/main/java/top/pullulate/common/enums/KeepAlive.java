package top.pullulate.common.enums;

/**
 * @功能描述: 菜单缓存枚举
 * @Author: pullulate
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public enum KeepAlive {

    ALIVE("0", "menu.keep.alive"), UNALIVE("1", "menu.unkeep.alive");

    private String code;

    private String label;

    KeepAlive(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public static Boolean keepAlive(String code) {
        return ALIVE.code.equals(code);
    }

}
