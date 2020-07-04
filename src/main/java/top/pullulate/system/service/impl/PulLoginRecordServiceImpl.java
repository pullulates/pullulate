package top.pullulate.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulLoginRecord;
import top.pullulate.system.mapper.PulLoginRecordMapper;
import top.pullulate.system.service.IPulLoginRecordService;

/**
 * @功能描述: 登录日志服务接口实现类
 * @Author: xuyong
 * @Date: 2020/7/4 16:41
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Service
public class PulLoginRecordServiceImpl extends ServiceImpl<PulLoginRecordMapper, PulLoginRecord> implements IPulLoginRecordService {
}
