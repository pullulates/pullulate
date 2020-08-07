package top.pullulate.web.controller.monitor;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.monitor.service.IPulJobService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.monitor.PulJobViewVo;
import top.pullulate.web.data.vo.monitor.PulJobVo;

import java.util.List;

/**
 * @功能描述:   定时任务前端控制器
 * @Author: xuyong
 * @Date: 2020/8/4 23:08
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/monitor/jobs")
public class PulJobController {

    private final IPulJobService jobService;

    /**
     * 获取定时任务列表数据
     *
     * @param jobVo 查询参数
     * @return
     */
    @GetMapping
    public P getJobList(PulJobVo jobVo) {
        List<PulJobViewVo> jobs = jobService.getJobList(jobVo);
        return P.data(jobs);
    }

    /**
     * 保存定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @PostMapping
    @OperationRecord(title = "定时任务-保存任务")
    public P saveJob(@RequestBody @Validated PulJobVo jobVo) {
        return jobService.saveJob(jobVo);
    }

    /**
     * 修改定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @PutMapping
    @OperationRecord(title = "定时任务-修改任务")
    public P updateJob(@RequestBody @Validated PulJobVo jobVo) {
        return jobService.updateJob(jobVo);
    }
}
