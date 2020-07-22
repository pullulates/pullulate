package top.pullulate.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.pullulate.monitor.entity.PulOperationRecord;
import top.pullulate.web.data.viewvo.monitor.PulLoginRecordViewVo;
import top.pullulate.web.data.vo.monitor.PulOperationRecordVo;

import java.util.List;

/**
 * @功能描述:   操作日志数据层
 * @Author: xuyong
 * @Date: 2020/7/4 17:25
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public interface PulOperationRecordMapper extends BaseMapper<PulOperationRecord> {

    /**
     * 获取操作日志分页数据
     *
     * @param operationRecordVo 操作日志参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulLoginRecordViewVo>> selectOperationRecordPage(@Param("operationRecordVo") PulOperationRecordVo operationRecordVo, Page page);
}
