package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @功能描述:   菜单实体类
 * @Author: pullulates
 * @Date: 2020/6/13 0013 9:59
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Getter
@Setter
public class PulMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 菜单主键 */
    @TableId
    private String menuId;

    /** 父级主键 */
    private String parentId;

    /** 标题 */
    private String title;

    /** 英文标题 */
    private String usTitle;

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
    private String hideChildrenInMenu;

    /** 是否隐藏面包屑与页面标题栏 */
    private String hiddenHeaderContent;

    /** 图标 */
    private String icon;

    /** 菜单类型 */
    private String menuType;

    /** 排序编号 */
    private Integer orderNum;

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

    /** 子菜单 */
    private transient List<PulMenu> children;

    public PulMenu() {
    }

    public PulMenu(String parentId, String title, String usTitle, String name, String path, String redirect, String component, String keepAlive, String permission, String target, String hidden, String hideChildrenInMenu, String hiddenHeaderContent, String icon, String menuType, Integer orderNum, String remark, String createBy, LocalDateTime createAt) {
        this.parentId = parentId;
        this.title = title;
        this.usTitle = usTitle;
        this.name = name;
        this.path = path;
        this.redirect = redirect;
        this.component = component;
        this.keepAlive = keepAlive;
        this.permission = permission;
        this.target = target;
        this.hidden = hidden;
        this.hideChildrenInMenu = hideChildrenInMenu;
        this.hiddenHeaderContent = hiddenHeaderContent;
        this.icon = icon;
        this.menuType = menuType;
        this.orderNum = orderNum;
        this.remark = remark;
        this.createBy = createBy;
        this.createAt = createAt;
    }
}
