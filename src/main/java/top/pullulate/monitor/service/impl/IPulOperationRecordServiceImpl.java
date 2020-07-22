package top.pullulate.monitor.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pullulate.monitor.entity.PulOperationRecord;
import top.pullulate.monitor.mapper.PulOperationRecordMapper;
import top.pullulate.monitor.service.IPulOperationRecordService;
import top.pullulate.web.data.viewvo.monitor.PulLoginRecordViewVo;
import top.pullulate.web.data.vo.monitor.PulOperationRecordVo;

import java.util.List;

/**
 * @功能描述:   操作日志服务接口实现类
 * @Author: xuyong
 * @Date: 2020/7/4 17:27
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Service
public class IPulOperationRecordServiceImpl extends ServiceImpl<PulOperationRecordMapper, PulOperationRecord> implements IPulOperationRecordService {

    /**
     * 获取操作日志分页数据
     *
     * @param operationRecordVo 操作日志参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<PulLoginRecordViewVo>> getOperationRecordPage(PulOperationRecordVo operationRecordVo, Page page) {
        return baseMapper.selectOperationRecordPage(operationRecordVo, page);
    }
}
