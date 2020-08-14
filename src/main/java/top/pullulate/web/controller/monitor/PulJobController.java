package top.pullulate.web.controller.monitor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.common.validate.Common;
import top.pullulate.common.validate.system.Job;
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
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
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
    @PreAuthorize("hasAuthority('monitor.job.query')")
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
    @PreAuthorize("hasAuthority('monitor.job.save')")
    public P saveJob(@RequestBody @Validated(value = Common.Save.class) PulJobVo jobVo) {
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
    @PreAuthorize("hasAuthority('monitor.job.edit')")
    public P updateJob(@RequestBody @Validated(value = Common.Update.class) PulJobVo jobVo) {
        return jobService.updateJob(jobVo);
    }

    /**
     * 执行定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @PostMapping("/execute")
    @OperationRecord(title = "定时任务-执行任务")
    @PreAuthorize("hasAuthority('monitor.job.execute')")
    public P executeJob(@RequestBody @Validated(value = Job.excute.class) PulJobVo jobVo) {
        return jobService.executeJob(jobVo);
    }

    /**
     * 暂停定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @PatchMapping("/pause")
    @OperationRecord(title = "定时任务-暂停任务")
    @PreAuthorize("hasAuthority('monitor.job.patch')")
    public P pauseJob(@RequestBody @Validated(value = Common.PatchStatus.class) PulJobVo jobVo) {
        return jobService.pauseJob(jobVo);
    }

    /**
     * 恢复定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @PatchMapping("/resume")
    @OperationRecord(title = "定时任务-恢复任务")
    @PreAuthorize("hasAuthority('monitor.job.patch')")
    public P resumeJob(@RequestBody @Validated(value = Common.PatchStatus.class) PulJobVo jobVo) {
        return jobService.resumeJob(jobVo);
    }

    /**
     * 删除定时任务
     *
     * @param jobId 定时任务主键
     * @return
     */
    @DeleteMapping
    @OperationRecord(title = "定时任务-删除任务")
    @PreAuthorize("hasAuthority('monitor.job.del')")
    public P deleteJob(String jobId) {
        return jobService.deleteJob(jobId);
    }

}
