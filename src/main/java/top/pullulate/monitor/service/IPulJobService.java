package top.pullulate.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.monitor.entity.PulJob;
import top.pullulate.web.data.viewvo.monitor.PulJobViewVo;
import top.pullulate.web.data.vo.monitor.PulJobVo;

import java.util.List;

/**
 * @功能描述:   定时任务服务接口
 * @Author: xuyong
 * @Date: 2020/8/4 23:11
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public interface IPulJobService extends IService<PulJob> {

    /**
     * 获取定时任务列表数据
     *
     * @param jobVo 查询参数
     * @return
     */
    List<PulJobViewVo> getJobList(PulJobVo jobVo);
}
