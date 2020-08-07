package top.pullulate.web.data.viewvo;

import lombok.Getter;
import lombok.Setter;

/**
 * @功能描述:   通用的统计视图类
 * @Author: xuyong
 * @Date: 2020/8/1 22:48
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Getter
@Setter
public class CommonStatisticsViewVo {

    /** 统计类别 */
    private String type;

    /** 统计日期 */
    private String date;

    /** 统计值 */
    private int value;

    public CommonStatisticsViewVo() {
    }

    public CommonStatisticsViewVo(String type, String date, int value) {
        this.type = type;
        this.date = date;
        this.value = value;
    }
}
