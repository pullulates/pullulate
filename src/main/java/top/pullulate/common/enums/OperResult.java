package top.pullulate.common.enums;

import top.pullulate.utils.MessageUtils;

import java.util.Arrays;

/**
 * @功能描述: 操作结果
 * @Author: pullulate
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public enum OperResult {

    SUCCESS("0", "operate.success"), ERROR("1", "operate.error");

    private String code;

    private String label;

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static String getLabel(String code) {
        return MessageUtils.get(Arrays.stream(OperResult.values()).filter(item -> item.code.equals(code)).map(item -> item.label).findFirst().get());
    }

    OperResult(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
