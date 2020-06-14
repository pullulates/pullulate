package top.pullulate.common.enums;

/**
 * @功能描述: 性别枚举
 * @Author: pullulates
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public enum Sex {

    MALE("0", "dict.sex.male"), FEMALE("1", "dict.sex.female"), SECRET("2", "dict.sex.secret"), UNKHNOWN("3", "dict.sex.unknown");

    private String code;

    private String label;

    Sex(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean wouldMale(String code) {
        return MALE.code.equals(code);
    }

    public static Boolean wouldFeMale(String code) {
        return FEMALE.code.equals(code);
    }
}
