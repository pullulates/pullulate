package top.pullulate.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.pullulate.monitor.entity.PulLoginRecord;
import top.pullulate.web.data.viewvo.monitor.PulLoginRecordViewVo;
import top.pullulate.web.data.vo.monitor.PulLoginRecordVo;

import java.util.List;

/**
 * @功能描述:   登录日志数据层
 * @Author: xuyong
 * @Date: 2020/6/18 10:34
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface PulLoginRecordMapper extends BaseMapper<PulLoginRecord> {

    /**
     * 获取登录日志分页数据
     *
     * @param loginRecordVo 登录日志参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulLoginRecordViewVo>> selectLoginRecordPage(@Param("loginRecordVo") PulLoginRecordVo loginRecordVo, Page page);

    /**
     * 根据日期获取ip数量
     *
     * @param localDate
     * @return
     */
    int selectIpCountByDate(String localDate);
}
