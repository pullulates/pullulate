package top.pullulate.core.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @功能描述:   定时任务调度基础接口
 * @Author: xuyong
 * @Date: 2020/8/4 21:52
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface BaseJob extends Job {

    void execute(JobExecutionContext context) throws JobExecutionException;
}
