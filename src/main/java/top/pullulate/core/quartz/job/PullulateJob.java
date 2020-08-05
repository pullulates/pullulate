package top.pullulate.core.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @功能描述:   定时任务创建实例
 * @Author: xuyong
 * @Date: 2020/8/4 21:54
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
public class PullulateJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("start running pullulateJob...");
    }
}
