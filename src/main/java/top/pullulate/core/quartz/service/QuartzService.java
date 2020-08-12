package top.pullulate.core.quartz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;
import top.pullulate.core.exception.QuartzJobException;
import top.pullulate.core.quartz.job.BaseJob;
import top.pullulate.monitor.entity.PulJob;
import java.util.*;

/**
 * @功能描述:   定时任务服务类
 * @Author: xuyong
 * @Date: 2020/8/4 21:51
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class QuartzService {

    private final Scheduler scheduler;

    /**
     * 创建job
     *
     * @param job   定时任务信息
     * @throws Exception
     */
    public void addJob(PulJob job) {
        try {
            scheduler.start();
            //构建job信息
            JobDetail jobDetail = null;
            Class clazz = Class.forName(job.getInvokeTarget());
            BaseJob baseJob = (BaseJob)clazz.newInstance();
            jobDetail = JobBuilder.newJob(baseJob.getClass()).withIdentity(job.getName(), job.getGroupName()).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroupName())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("创建定时任务失败： ", e);
            throw new QuartzJobException(e.getMessage());
        }

    }


    /**
     * 创建job，可传参
     *
     * @param clazz          任务类
     * @param jobName        任务名称
     * @param jobGroupName   任务所在组名称
     * @param cronExpression cron表达式
     * @param argMap         map形式参数
     * @throws Exception
     */
    public void addJob(Class clazz, String jobName, String jobGroupName, String cronExpression, Map<String, Object> argMap) {
        try {
            // 启动调度器
            scheduler.start();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(((BaseJob) clazz.newInstance()).getClass()).withIdentity(jobName, jobGroupName).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(scheduleBuilder).build();
            //获得JobDataMap，写入数据
            trigger.getJobDataMap().putAll(argMap);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("创建定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }

    /**
     * 暂停job
     *
     * @param job        任务信息
     * @throws SchedulerException
     */
    public void pauseJob(PulJob job) {
        try {
            scheduler.pauseJob(JobKey.jobKey(job.getName(), job.getGroupName()));
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }

    /**
     * 恢复job
     *
     * @param job        任务信息
     * @throws SchedulerException
     */
    public void resumeJob(PulJob job) {
        try {
            scheduler.resumeJob(JobKey.jobKey(job.getName(), job.getGroupName()));
        } catch (SchedulerException e) {
            log.error("恢复定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }


    /**
     * job 更新,只更新频率
     *
     * @param job        任务信息
     * @throws Exception
     */
    public void updateJob(PulJob job) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroupName());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("修改定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }

    /**
     * job 更新,更新频率和参数
     *
     * @param jobName        任务名称
     * @param jobGroupName   任务所在组名称
     * @param cronExpression cron表达式
     * @param argMap         参数
     * @throws Exception
     */
    public void updateJob(String jobName, String jobGroupName, String cronExpression, Map<String, Object> argMap) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //修改map
            trigger.getJobDataMap().putAll(argMap);
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }

    /**
     * job 更新,只更新更新参数
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务所在组名称
     * @param argMap       参数
     * @throws Exception
     */
    public void updateJob(String jobName, String jobGroupName, Map<String, Object> argMap) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //修改map
            trigger.getJobDataMap().putAll(argMap);
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }


    /**
     * job 删除
     *
     * @param job      任务信息
     * @throws Exception
     */
    public void deleteJob(PulJob job) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(job.getName(), job.getGroupName()));
            scheduler.unscheduleJob(TriggerKey.triggerKey(job.getName(), job.getGroupName()));
            scheduler.deleteJob(JobKey.jobKey(job.getName(), job.getGroupName()));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }


    /**
     * 启动所有定时任务
     *
     */
    public void startAllJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            log.error("启动定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }

    /**
     * 关闭所有定时任务
     *
     */
    public void shutdownAllJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            log.error("关闭定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }

    /**
     * 立即执行一次任务
     *
     * @param job   任务信息
     */
    public void excuteAtOnce (PulJob job) {
        try {
            scheduler.triggerJob(JobKey.jobKey(job.getName(), job.getGroupName()));
        } catch (SchedulerException e) {
            log.error("执行定时任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }

    /**
     * 获取所有任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<Map<String, Object>> getAllJob() {
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            List<Map<String, Object>> jobList = new ArrayList<>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> job = new HashMap<>();
                    job.put("jobName", jobKey.getName());
                    job.put("jobGroupName", jobKey.getGroup());
                    job.put("trigger", trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.put("jobStatus", triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.put("cronExpression", cronExpression);
                    }
                    jobList.add(job);
                }
            }
            return jobList;
        } catch (SchedulerException e) {
            log.error("获取所有任务失败：", e);
            throw new QuartzJobException(e.getMessage());
        }
    }
}
