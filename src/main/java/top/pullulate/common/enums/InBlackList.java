package top.pullulate.common.enums;

/**
 * @功能描述: 是否拉黑
 * @Author: pullulate
 * @Date: 2020年9月18日10:06:18
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public enum InBlackList {

    NOT_IN_BLACK_LIST("0", "正常"), IN_BLACK_LIST("1", "已拉黑");

    private String code;

    private String label;

    public String getCode() {
        return code;
    }

    InBlackList(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
