package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   菜单实体类
 * @Author: pullulates
 * @Date: 2020/6/13 0013 9:59
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulMenu {

    /** 菜单主键 */
    @TableId
    private String menuId;

    /** 父级主键 */
    private String parentId;

    /** 标题 */
    private String title;

    /** 名称 */
    private String name;

    /** 访问路径 */
    private String path;

    /** 重定向地址 */
    private String redirect;

    /** 组件地址 */
    private String component;

    /** 是否缓存 */
    private String keepAlive;

    /** 权限标识 */
    private String permission;

    /** 打开方式 */
    private String target;

    /** 是否隐藏 */
    private String hidden;

    /** 强制菜单显示为Item而不是SubItem(配合 meta.hidden) */
    private String hideChildenInMenu;

    /** 是否隐藏面包屑与页面标题栏 */
    private String hiddenHeaderContent;

    /** 图标 */
    private String icon;

    /** 状态 */
    private String status;

    /** 删除标识 */
    private String deleteFlag;

    /** 备注信息 */
    private String remark;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createAt;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    private LocalDateTime updateAt;
}
