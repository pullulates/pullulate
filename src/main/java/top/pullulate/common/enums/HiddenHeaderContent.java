package top.pullulate.common.enums;

/**
 * @功能描述: 是否显示面包屑与页面标题栏枚举
 * @Author: pullulates
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public enum HiddenHeaderContent {

    SHOW("0", "menu.show.headercontent"), HIDE("1", "menu.hidden.headercontent");

    private String code;

    private String label;

    HiddenHeaderContent(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean hiddenHeaderContent(String code) {
        return HIDE.code.equals(code);
    }

}
