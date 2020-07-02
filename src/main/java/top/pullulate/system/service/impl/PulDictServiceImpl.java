package top.pullulate.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.system.mapper.PulDictDataMapper;
import top.pullulate.system.mapper.PulDictTypeMapper;
import top.pullulate.system.service.IPulDictService;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.PulDictTypeViewVo;
import top.pullulate.web.data.vo.PulDictVo;
import java.util.List;

/**
 * @功能描述:   数据字典接口服务实现类
 * @Author: xuyong
 * @Date: 2020/7/2 16:08
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PulDictServiceImpl implements IPulDictService {

    private final PulDictTypeMapper dictTypeMapper;

    private final PulDictDataMapper dictDataMapper;

    /**
     * 获取字典类别分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<PulDictTypeViewVo>> getDictTypePage(PulDictVo dictVo, Page page) {
        return dictTypeMapper.selectDictTypePage(dictVo, page);
    }

    /**
     * 获取字典数据分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<PulDictDataViewVo>> getDictDataPage(PulDictVo dictVo, Page page) {
        return dictDataMapper.selectDictDataPage(dictVo, page);
    }
}
