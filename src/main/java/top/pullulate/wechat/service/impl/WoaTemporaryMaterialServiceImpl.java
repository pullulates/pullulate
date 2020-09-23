package top.pullulate.wechat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.viewvo.wechat.WoaTemporaryMaterialViewVo;
import top.pullulate.web.data.vo.wechat.WoaTemporaryMaterialVo;
import top.pullulate.wechat.entity.WoaTemporaryMaterial;
import top.pullulate.wechat.mapper.WoaTemporaryMaterialMapper;
import top.pullulate.wechat.service.IWoaTemporaryMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @功能描述:   微信公众号临时素材服务接口实现类
 * @Author: pullulate
 * @Date: 2020年9月23日15:01:58
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Service
public class WoaTemporaryMaterialServiceImpl extends ServiceImpl<WoaTemporaryMaterialMapper, WoaTemporaryMaterial> implements IWoaTemporaryMaterialService {

    /**
     * 获取临时素材的分页数据
     *
     * @param materialVo    查询参数
     * @param page          分页参数
     * @return
     */
    @Override
    public IPage<List<WoaTemporaryMaterialViewVo>> getTemporaryMaterialPage(WoaTemporaryMaterialVo materialVo, Page page) {
        IPage<List<WoaTemporaryMaterialViewVo>> pages = baseMapper.getTemporaryMaterialPage(materialVo, page);
        return pages;
    }
}
