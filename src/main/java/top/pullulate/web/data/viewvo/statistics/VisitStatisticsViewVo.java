package top.pullulate.web.data.viewvo.statistics;

import lombok.Getter;
import lombok.Setter;

/**
 * @功能描述:   系统访问统计视图类
 * @Author: xuyong
 * @Date: 2020/8/15 11:46
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Getter
@Setter
public class VisitStatisticsViewVo {

    /** 统计类别，如统计日期 */
    private String types;

    /** 总数量 */
    private int total;

    /** ip数量 */
    private int ip;

    public VisitStatisticsViewVo(String types, int total, int ip) {
        this.types = types;
        this.total = total;
        this.ip = ip;
    }
}
