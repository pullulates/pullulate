package top.pullulate.monitor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.pullulate.core.quartz.service.QuartzService;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.monitor.entity.PulJob;
import top.pullulate.monitor.mapper.PulJobMapper;
import top.pullulate.monitor.service.IPulJobService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.monitor.PulJobViewVo;
import top.pullulate.web.data.vo.monitor.PulJobVo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @功能描述:
 * @Author: xuyong
 * @Date: 2020/8/4 23:12
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Service
@RequiredArgsConstructor
public class PulJobServiceImpl extends ServiceImpl<PulJobMapper, PulJob> implements IPulJobService {

    private final QuartzService quartzService;

    private final TokenUtils tokenUtils;

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
        job.setCreateAt(LocalDateTime.now());
        job.setCreateBy(tokenUtils.getUserName());
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
        job.setUpdateAt(LocalDateTime.now());
        job.setUpdateBy(tokenUtils.getUserName());
        return P.p(updateById(job));
    }
}
