package top.pullulate.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.system.service.IPulDictService;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.PulDictTypeViewVo;
import top.pullulate.web.data.vo.PulDictVo;

import java.util.List;

/**
 * @功能描述:   数据字典前端控制器
 * @Author: xuyong
 * @Date: 2020/7/2 15:50
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequestMapping("/system/dict")
@RequiredArgsConstructor
public class PulDictController {

    private final IPulDictService dictService;

    /**
     * 获取字典类别分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    @GetMapping("/types")
    public P getDictTypePage(PulDictVo dictVo, Page page) {
        IPage<List<PulDictTypeViewVo>> pages = dictService.getDictTypePage(dictVo, page);
        return P.data(pages);
    }

    /**
     * 获取字典数据分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    @GetMapping("/datas")
    public P getDictDataPage(PulDictVo dictVo, Page page) {
        IPage<List<PulDictDataViewVo>> pages = dictService.getDictDataPage(dictVo, page);
        return P.data(pages);
    }
}
