package top.pullulate.common.enums;

import cn.hutool.core.util.IdcardUtil;

/**
 * @功能描述: 性别枚举
 * @Author: pullulate
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
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

    public static String getSexFromIdcard(String idcard) {
        String sex = String.valueOf(IdcardUtil.getGenderByIdCard(idcard));
        return MALE.code.equals(sex) ? FEMALE.code : MALE.code;
    }
}
