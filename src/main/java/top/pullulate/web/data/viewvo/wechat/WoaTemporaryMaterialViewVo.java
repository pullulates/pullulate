package top.pullulate.web.data.viewvo.wechat;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   微信公众号临时素材视图类
 * @Author: pullulate
 * @Date: 2020年9月23日15:01:58
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class WoaTemporaryMaterialViewVo {

    /**
     * 素材主键
     */
    private String materialId;

    /**
     * 微信公众号主键
     */
    private String woaId;

    /**
     * 微信返回的素材标识
     */
    private String mediaId;

    /**
     * 素材类型
     */
    private String materialType;

    /**
     * 上传时间
     */
    private LocalDateTime uploadAt;

    /**
     * 过期时间
     */
    private LocalDateTime expireAt;

    /**
     * 本地存储地址
     */
    private String locationUrl;

    /**
     * 名称
     */
    private String name;

    /**
     * 原名
     */
    private String originalName;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    private String createBy;

}
