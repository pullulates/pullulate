package top.pullulate.monitor.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pullulate.monitor.entity.PulLoginRecord;
import top.pullulate.monitor.mapper.PulLoginRecordMapper;
import top.pullulate.monitor.service.IPulLoginRecordService;
import top.pullulate.utils.LocalDateUtils;
import top.pullulate.web.data.viewvo.monitor.PulLoginRecordViewVo;
import top.pullulate.web.data.viewvo.statistics.VisitStatisticsViewVo;
import top.pullulate.web.data.vo.monitor.PulLoginRecordVo;
import top.pullulate.web.data.vo.statistics.CommonStatisticsQueryVo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @功能描述: 登录日志服务接口实现类
 * @Author: xuyong
 * @Date: 2020/7/4 16:41
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Service
public class PulLoginRecordServiceImpl extends ServiceImpl<PulLoginRecordMapper, PulLoginRecord> implements IPulLoginRecordService {

    /**
     * 获取登录日志分页数据
     *
     * @param loginRecordVo 登录日志参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<PulLoginRecordViewVo>> getLoginRecordPage(PulLoginRecordVo loginRecordVo, Page page) {
        return baseMapper.selectLoginRecordPage(loginRecordVo, page);
    }

    /**
     * 获取用户访问统计数据
     *
     * @return
     */
    @Override
    public List<VisitStatisticsViewVo> getUserAccessStatistics(CommonStatisticsQueryVo queryVo) {
        List<LocalDate> rangeDate;
        if (ObjectUtil.isNull(queryVo.getStartDate())) {
            rangeDate = LocalDateUtils.getRecentSevenLocalDate();
        } else {
            rangeDate = LocalDateUtils.getLocaDatesBetweenDate(queryVo.getStartDate(), queryVo.getEndDate());
        }
        List<VisitStatisticsViewVo> results = new ArrayList<>(rangeDate.size());
        rangeDate.forEach(localDate -> {
            int total = count(Wrappers.<PulLoginRecord>lambdaQuery()
                    .apply("date_format(login_time, '%Y-%m-%d')={0}",localDate.toString()));
            int ip = count(Wrappers.<PulLoginRecord>lambdaQuery()
                    .apply("date_format(login_time, '%Y-%m-%d')={0}", localDate.toString())
                    .groupBy(PulLoginRecord::getIp));
            VisitStatisticsViewVo visit = new VisitStatisticsViewVo(localDate.toString(), total, ip);
            results.add(visit);
        });
        return results;
    }
}
