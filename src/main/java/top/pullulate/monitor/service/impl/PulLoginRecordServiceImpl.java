package top.pullulate.monitor.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pullulate.monitor.entity.PulLoginRecord;
import top.pullulate.monitor.mapper.PulLoginRecordMapper;
import top.pullulate.monitor.service.IPulLoginRecordService;
import top.pullulate.web.data.viewvo.monitor.PulLoginRecordViewVo;
import top.pullulate.web.data.vo.monitor.PulLoginRecordVo;

import java.util.List;

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
}
