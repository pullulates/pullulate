package top.pullulate.common.enums;

/**
 * @功能描述: 数据状态
 * @Author: pullulates
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public enum DataStatus {

    NORMAL("0", "data.status.normal"), DISABLED("1", "data.status.disabled");

    private String code;

    private String label;

    DataStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean disabled(String code) {
        return DISABLED.code.equals(code);
    }
}
