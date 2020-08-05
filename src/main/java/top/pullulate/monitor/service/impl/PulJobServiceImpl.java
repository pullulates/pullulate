package top.pullulate.monitor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.pullulate.monitor.entity.PulJob;
import top.pullulate.monitor.mapper.PulJobMapper;
import top.pullulate.monitor.service.IPulJobService;
import top.pullulate.web.data.viewvo.monitor.PulJobViewVo;
import top.pullulate.web.data.vo.monitor.PulJobVo;

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
}
