package top.pullulate.utils;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @功能描述:   Unicode解码
 * @Author: pullulates
 * @Date: 2020/7/4 0004 11:43
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public class UnicodeUtils {

    /**
     * 转为unicode字符集
     *
     * @param string    中文字符
     * @return  unicode字符集
     */
    public static String unicodeEncode(String string) {
        if (StrUtil.isBlank(string)) {
            return StrUtil.EMPTY;
        }
        char[] utfBytes = string.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /**
     * 转为中文
     *
     * @param string    unicode字符集
     * @return  中文字符
     */
    public static String unicodeDecode(String string) {
        if (StrUtil.isBlank(string)) {
            return StrUtil.EMPTY;
        }
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }
}
