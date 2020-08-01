package top.pullulate.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.monitor.entity.PulLoginRecord;
import top.pullulate.web.data.viewvo.CommonStatisticsViewVo;
import top.pullulate.web.data.viewvo.monitor.PulLoginRecordViewVo;
import top.pullulate.web.data.vo.monitor.PulLoginRecordVo;
import top.pullulate.web.data.vo.statistics.CommonStatisticsQueryVo;

import java.util.List;

/**
 * @功能描述:   登录日志服务接口
 * @Author: xuyong
 * @Date: 2020/7/4 16:41
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public interface IPulLoginRecordService extends IService<PulLoginRecord> {

    /**
     * 获取登录日志分页数据
     *
     * @param loginRecordVo 登录日志参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulLoginRecordViewVo>> getLoginRecordPage(PulLoginRecordVo loginRecordVo, Page page);

    /**
     * 获取用户访问统计数据
     *
     * @return
     */
    List<CommonStatisticsViewVo> getUserAccessStatistics(CommonStatisticsQueryVo queryVo);
}
