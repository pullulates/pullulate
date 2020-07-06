package top.pullulate.common.enums;

/**
 * @功能描述: 字典类型枚举
 * @Author: pullulates
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public enum DictType {

    DEFAULT("0", "默认字典"), BUSINESS("1", "业务字典");

    private String code;

    private String label;

    DictType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean willDefault(String code) {
        return DEFAULT.code.equals(code);
    }

}
