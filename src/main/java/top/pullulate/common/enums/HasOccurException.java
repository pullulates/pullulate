package top.pullulate.common.enums;

/**
 * @功能描述: 是否发生异常
 * @Author: pullulate
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public enum HasOccurException {

    NORMAL("0", "exception.not.occured"), OCCURED("1", "exception.occured");

    private String code;

    private String label;

    HasOccurException(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public static Boolean hasOccuredException(String code) {
        return OCCURED.code.equals(code);
    }
}
