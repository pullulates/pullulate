package top.pullulate.common.enums;

/**
 * @功能描述: 是否被锁定
 * @Author: pullulates
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public enum LockFlag {

    UNLOCKED("0", "user.unlocked"), LOCKED("1", "user.locked");

    private String code;

    private String label;

    LockFlag(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean hasLocked(String code) {
        return LOCKED.code.equals(code);
    }
}
