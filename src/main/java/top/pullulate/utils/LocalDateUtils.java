package top.pullulate.utils;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @功能描述:   LocalDate工具类
 * @Author: xuyong
 * @Date: 2020/8/1 22:56
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public class LocalDateUtils {

    /**
     * 最近7天的所有日期
     *
     * @return
     */
    public static List<String> getRecentSevenDate() {
        List<String> list = new ArrayList<>();
        LocalDate start = LocalDate.now().minusDays(6);
        Stream.iterate(start, d -> d.plusDays(1)).limit(7).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }
    /**
     * 最近7天的所有日期
     *
     * @return
     */
    public static List<LocalDate> getRecentSevenLocalDate() {
        List<LocalDate> list = new ArrayList<>();
        LocalDate start = LocalDate.now().minusDays(6);
        Stream.iterate(start, d -> d.plusDays(1)).limit(7).forEach(f -> {
            list.add(f);
        });
        return list;
    }

    /**
     * 获取两个日期间隔的所有日期
     *
     * @param start 格式必须为'2018-01-25'
     * @param end 格式必须为'2018-01-25'
     * @return
     */
    public static List<String> getDatesBetweenDate(LocalDate start, LocalDate end){
        List<String> list = new ArrayList<>();
        long distance = ChronoUnit.DAYS.between(start, end);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(start, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }

    /**
     * 获取两个日期间隔的所有日期
     *
     * @param start 格式必须为'2018-01-25'
     * @param end 格式必须为'2018-01-25'
     * @return
     */
    public static List<LocalDate> getLocaDatesBetweenDate(LocalDate start, LocalDate end){
        List<LocalDate> list = new ArrayList<>();
        long distance = ChronoUnit.DAYS.between(start, end);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(start, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> {
            list.add(f);
        });
        return list;
    }

    /**
     * 将时间戳转换为LocalDateTime
     *
     * @param timestamp 时间戳
     * @return
     */
    public static LocalDateTime getLocalDateTimeByTimestamp(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
    }
}
