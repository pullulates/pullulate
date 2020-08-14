package top.pullulate.web.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.system.service.IPulDictService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.system.PulDictTypeViewVo;
import top.pullulate.web.data.vo.system.PulDictDataVo;
import top.pullulate.web.data.vo.system.PulDictTypeVo;
import java.util.List;

/**
 * @功能描述:   数据字典前端控制器
 * @Author: xuyong
 * @Date: 2020/7/2 15:50
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
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
    @PreAuthorize("hasAuthority('system.dict.query')")
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
    @PreAuthorize("hasAuthority('system.dict.query')")
    public P getDictDataPage(PulDictDataVo dictVo, Page page) {
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
    @PreAuthorize("hasAuthority('system.dict.save')")
    public P saveDictType(@Validated @RequestBody PulDictTypeVo dictTypeVo) {
        return dictService.saveDictType(dictTypeVo);
    }

    /**
     * 修改字典类别数据
     *
     * @param dictTypeVo    字典类别数据
     * @return
     */
    @PutMapping("/types")
    @OperationRecord(title = "数据字典-修改字典分类")
    @PreAuthorize("hasAuthority('system.dict.edit')")
    public P updateDictType(@Validated @RequestBody PulDictTypeVo dictTypeVo) {
        return dictService.updateDictType(dictTypeVo);
    }

    /**
     * 保存字典数据
     *
     * @param dictDataVo    字典数据
     * @return
     */
    @PostMapping("/datas")
    @OperationRecord(title = "数据字典-保存字典数据")
    @PreAuthorize("hasAuthority('system.dict.save')")
    public P saveDictData(@Validated @RequestBody PulDictDataVo dictDataVo) {
        return dictService.saveDictData(dictDataVo);
    }

    /**
     * 修改字典数据
     *
     * @param dictDataVo    字典数据
     * @return
     */
    @PutMapping("/datas")
    @OperationRecord(title = "数据字典-修改字典数据")
    @PreAuthorize("hasAuthority('system.dict.edit')")
    public P updateDictData(@Validated @RequestBody PulDictDataVo dictDataVo) {
        return dictService.updateDictData(dictDataVo);
    }

    /**
     * 修改字典类别的状态
     *
     * @param dictTypeVo    字典类别主键 目标状态
     * @return
     */
    @PatchMapping("/types")
    @OperationRecord(title = "数据字典-修改字典类别状态")
    @PreAuthorize("hasAuthority('system.dict.patch')")
    public P updateDictTypeStatus(@RequestBody PulDictTypeVo dictTypeVo) {
        return  dictService.updateDictTypeStatus(dictTypeVo);
    }

    /**
     * 删除字典类别
     *
     * @param dictTypeId    字典类别主键
     * @return
     */
    @DeleteMapping("/types")
    @OperationRecord(title = "数据字典-删除字典类别")
    @PreAuthorize("hasAuthority('system.dict.del')")
    public P deleteDictType(String dictTypeId) {
        return dictService.deleteDictType(dictTypeId);
    }

    /**
     * 修改字典数据的状态
     *
     * @param dictDataVo    字典数据主键 目标状态
     * @return
     */
    @PatchMapping("/datas")
    @OperationRecord(title = "数据字典-修改字典数据状态")
    @PreAuthorize("hasAuthority('system.dict.patch')")
    public P updateDictDataStatus(@RequestBody PulDictDataVo dictDataVo) {
        return  dictService.updateDictDataStatus(dictDataVo);
    }

    /**
     * 删除字典数据
     *
     * @param dictDataId    字典数据主键
     * @return
     */
    @DeleteMapping("/datas")
    @OperationRecord(title = "数据字典-删除字典数据")
    @PreAuthorize("hasAuthority('system.dict.del')")
    public P deleteDictData(String dictDataId) {
        return dictService.deleteDictData(dictDataId);
    }
}
