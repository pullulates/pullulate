package top.pullulate.monitor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pullulate.core.quartz.service.QuartzService;
import top.pullulate.monitor.entity.PulJob;
import top.pullulate.monitor.mapper.PulJobMapper;
import top.pullulate.monitor.service.IPulJobService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.monitor.PulJobViewVo;
import top.pullulate.web.data.vo.monitor.PulJobVo;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @功能描述:
 * @Author: xuyong
 * @Date: 2020/8/4 23:12
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Service
@RequiredArgsConstructor
public class PulJobServiceImpl extends ServiceImpl<PulJobMapper, PulJob> implements IPulJobService {

    private final QuartzService quartzService;

    /**
     * 获取定时任务列表数据
     *
     * @param jobVo 查询参数
     * @return
     */
    @Override
    public List<PulJobViewVo> getJobList(PulJobVo jobVo) {
        List<PulJobViewVo> jobs = list().stream()
                .map(item -> BeanUtil.toBean(item, PulJobViewVo.class))
                .collect(Collectors.toList());
        return jobs;
    }

    /**
     * 保存定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @Override
    public P saveJob(PulJobVo jobVo) {
        PulJob job = BeanUtil.toBean(jobVo, PulJob.class);
        int count = count(Wrappers.<PulJob>lambdaQuery().eq(PulJob::getName, job.getName()));
        if (count > 0) {
            return P.error("定时任务已存在!");
        }
        quartzService.deleteJob(job);
        quartzService.addJob(job);
        return P.p(save(job));
    }

    /**
     * 修改定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @Override
    public P updateJob(PulJobVo jobVo) {
        PulJob job = BeanUtil.toBean(jobVo, PulJob.class);
        int count = count(Wrappers.<PulJob>lambdaQuery()
                .ne(PulJob::getJobId, job.getJobId())
                .eq(PulJob::getName, job.getName()));
        if (count > 0) {
            return P.error("定时任务已存在!");
        }
        quartzService.updateJob(job);
        return P.p(updateById(job));
    }

    /**
     * 执行定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @Override
    public P executeJob(PulJobVo jobVo) {
        PulJob job = getById(jobVo.getJobId());
        if (ObjectUtil.isNull(job)) {
            return P.error("定时任务不存在！");
        }
        quartzService.excuteAtOnce(job);
        return P.success();
    }

    /**
     * 暂停定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P pauseJob(PulJobVo jobVo) {
        PulJob job = getById(jobVo.getJobId());
        if (ObjectUtil.isNull(job)) {
            return P.error("定时任务不存在！");
        }
        job.setStatus(jobVo.getStatus());
        updateById(job);
        quartzService.pauseJob(job);
        return P.success();
    }

    /**
     * 恢复定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P resumeJob(PulJobVo jobVo) {
        PulJob job = getById(jobVo.getJobId());
        if (ObjectUtil.isNull(job)) {
            return P.error("定时任务不存在！");
        }
        job.setStatus(jobVo.getStatus());
        updateById(job);
        quartzService.resumeJob(job);
        return P.success();
    }

    /**
     * 删除定时任务
     *
     * @param jobId 定时任务主键
     * @return
     */
    @Override
    public P deleteJob(String jobId) {
        PulJob job = getById(jobId);
        if (ObjectUtil.isNull(job)) {
            return P.error("定时任务不存在！");
        }
        removeById(jobId);
        return P.success();
    }
}
