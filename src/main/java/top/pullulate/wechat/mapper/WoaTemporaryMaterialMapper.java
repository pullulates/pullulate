package top.pullulate.wechat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.pullulate.web.data.viewvo.wechat.WoaTemporaryMaterialViewVo;
import top.pullulate.web.data.vo.wechat.WoaTemporaryMaterialVo;
import top.pullulate.wechat.entity.WoaTemporaryMaterial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @功能描述:   微信公众号临时素材数据层
 * @Author: pullulate
 * @Date: 2020年9月23日15:01:58
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface WoaTemporaryMaterialMapper extends BaseMapper<WoaTemporaryMaterial> {

    /**
     * 获取临时素材的分页数据
     *
     * @param materialVo    查询参数
     * @param page          分页参数
     * @return
     */
    IPage<List<WoaTemporaryMaterialViewVo>> getTemporaryMaterialPage(@Param("materialVo") WoaTemporaryMaterialVo materialVo, Page page);
}
