package top.pullulate.common.enums;

/**
 * @功能描述: 数据状态
 * @Author: pullulate
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
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
