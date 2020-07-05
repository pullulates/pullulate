package top.pullulate.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulOperationRecord;
import top.pullulate.system.mapper.PulOperationRecordMapper;
import top.pullulate.system.service.IPulOperationRecordService;

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
}
