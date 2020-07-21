package top.pullulate.web.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.monitor.entity.PulOperationRecord;
import top.pullulate.monitor.service.IPulLoginRecordService;
import top.pullulate.monitor.service.IPulOperationRecordService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.monitor.PulLoginRecordViewVo;
import top.pullulate.web.data.vo.monitor.PulLoginRecordVo;
import top.pullulate.web.data.vo.monitor.PulOperationRecordVo;

import java.util.List;

/**
 * @功能描述:   系统日志前端控制器
 * @Author: xuyong
 * @Date: 2020/7/21 16:37
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/monitor/logs")
public class LogController {

    private final IPulLoginRecordService loginRecordService;

    private final IPulOperationRecordService operationRecordService;

    /**
     * 获取登录日志分页数据
     *
     * @param loginRecordVo 登录日志参数
     * @param page  分页参数
     * @return
     */
    @GetMapping("/login")
    public P getLoginRecordPage(PulLoginRecordVo loginRecordVo, Page page) {
        IPage<List<PulLoginRecordViewVo>> records = loginRecordService.getLoginRecordPage(loginRecordVo, page);
        return P.data(records);
    }

    /**
     * 获取操作日志分页数据
     *
     * @param operationRecordVo 操作日志参数
     * @param page  分页参数
     * @return
     */
    @GetMapping("/operation")
    public P getOperationRecordPage(PulOperationRecordVo operationRecordVo, Page page) {
        IPage<List<PulLoginRecordViewVo>> records = operationRecordService.getOperationRecordPage(operationRecordVo, page);
        return P.data(records);
    }

}
