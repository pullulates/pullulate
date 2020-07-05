package top.pullulate.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.system.service.IPulDictService;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.PulDictTypeViewVo;
import top.pullulate.web.data.vo.PulDictTypeVo;

import javax.validation.Valid;
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
    public P getDictTypePage(PulDictTypeVo dictVo, Page page) {
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
    public P getDictDataPage(PulDictTypeVo dictVo, Page page) {
        IPage<List<PulDictDataViewVo>> pages = dictService.getDictDataPage(dictVo, page);
        return P.data(pages);
    }

    /**
     * 获取建议的排序编号
     *
     * @param type  类型，代表获取的是字典类别还是字典数据的排序编号
     * @param dictTypeId  字典类别主键
     * @return
     */
    @GetMapping("/suggest-ordernum")
    public P getSuggestOrderNum(String type, String dictTypeId) {
        int orderNum = dictService.getSuggestOrderNum(type, dictTypeId);
        return P.data(orderNum);
    }

    /**
     * 保存字典类别数据
     *
     * @param dictTypeVo    字典类别数据
     * @return
     */
    @PostMapping("/types")
    @OperationRecord(title = "数据字典-保存字典分类")
    public P saveDictType(@Validated @RequestBody PulDictTypeVo dictTypeVo) {
        return dictService.saveDictType(dictTypeVo);
    }
}
