package top.pullulate.common.enums;

/**
 * @功能描述: 菜单类型枚举
 * @Author: pullulates
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public enum MenuType {

    DIRECTORY("0", "menu.type.directory"), MENU("1", "menu.type.menu"), BUTTON("2", "menu.type.button");

    private String code;

    private String label;

    MenuType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean directory(String code) {
        return DIRECTORY.code.equals(code);
    }

    public static Boolean menu(String code) {
        return MENU.code.equals(code);
    }

    public static Boolean button(String code) {
        return BUTTON.code.equals(code);
    }

}
