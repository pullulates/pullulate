package top.pullulate.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.monitor.entity.PulJob;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.monitor.PulJobViewVo;
import top.pullulate.web.data.vo.monitor.PulJobVo;

import java.util.List;

/**
 * @功能描述:   定时任务服务接口
 * @Author: xuyong
 * @Date: 2020/8/4 23:11
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface IPulJobService extends IService<PulJob> {

    /**
     * 获取定时任务列表数据
     *
     * @param jobVo 查询参数
     * @return
     */
    List<PulJobViewVo> getJobList(PulJobVo jobVo);

    /**
     * 保存定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    P saveJob(PulJobVo jobVo);

    /**
     * 修改定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    P updateJob(PulJobVo jobVo);

    /**
     * 执行定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    P executeJob(PulJobVo jobVo);

    /**
     * 暂停定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    P pauseJob(PulJobVo jobVo);

    /**
     * 恢复定时任务
     *
     * @param jobVo 任务参数
     * @return
     */
    P resumeJob(PulJobVo jobVo);

    /**
     * 删除定时任务
     *
     * @param jobId 定时任务主键
     * @return
     */
    P deleteJob(String jobId);
}
