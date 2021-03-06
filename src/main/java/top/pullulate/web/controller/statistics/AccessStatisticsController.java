package top.pullulate.web.controller.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.monitor.service.IPulLoginRecordService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.statistics.VisitStatisticsViewVo;
import top.pullulate.web.data.vo.statistics.CommonStatisticsQueryVo;

import java.util.List;

/**
 * @功能描述:   访问统计前端控制器
 * @Author: xuyong
 * @Date: 2020/8/1 22:27
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics/access")
public class AccessStatisticsController {

    private final IPulLoginRecordService loginRecordService;

    /**
     * 获取用户访问统计数据
     *
     * @return
     */
    @GetMapping("/users")
    public P getUserAccessStatistics(CommonStatisticsQueryVo queryVo) {
        List<VisitStatisticsViewVo> result = loginRecordService.getUserAccessStatistics(queryVo);
        return P.data(result);
    }
}
