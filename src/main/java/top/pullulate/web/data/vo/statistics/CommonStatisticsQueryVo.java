package top.pullulate.web.data.vo.statistics;

import lombok.Data;

import java.time.LocalDate;

/**
 * @功能描述:   通用的统计查询参数接收类
 * @Author: xuyong
 * @Date: 2020/8/1 22:33
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class CommonStatisticsQueryVo {

    /** 开始日期，精确到日 */
    private LocalDate startDate;

    /** 结束日期，精确到日 */
    private LocalDate endDate;
}
