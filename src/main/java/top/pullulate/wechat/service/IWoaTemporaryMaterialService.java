package top.pullulate.wechat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.viewvo.wechat.WoaTemporaryMaterialViewVo;
import top.pullulate.web.data.vo.wechat.WoaTemporaryMaterialVo;
import top.pullulate.wechat.entity.WoaTemporaryMaterial;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @功能描述:   微信公众号临时素材服务接口
 * @Author: pullulate
 * @Date: 2020年9月23日15:01:58
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface IWoaTemporaryMaterialService extends IService<WoaTemporaryMaterial> {

    /**
     * 获取临时素材的分页数据
     *
     * @param materialVo    查询参数
     * @param page          分页参数
     * @return
     */
    IPage<List<WoaTemporaryMaterialViewVo>> getTemporaryMaterialPage(WoaTemporaryMaterialVo materialVo, Page page);
}
