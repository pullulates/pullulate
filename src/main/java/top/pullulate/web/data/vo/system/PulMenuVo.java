package top.pullulate.web.data.vo.system;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @功能描述:   菜单参数接收类
 * @Author: xuyong
 * @Date: 2020/7/9 8:25
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulMenuVo {

    /** 菜单主键 */
    private String menuId;

    /** 父级主键 */
    private String parentId;

    /** 标题 */
    @NotBlank(message = "路由标题不能为空")
    @Size(max = 50, message = "路由标题最多50字")
    private String title;

    /** 英文标题 */
    @NotBlank(message = "路由英文标题不能为空")
    @Size(max = 50, message = "路由英文标题最多50字")
    private String usTitle;

    /** 名称 */
    @NotBlank(message = "路由名称不能为空")
    @Size(max = 50, message = "路由名称最多50字")
    private String name;

    /** 访问路径 */
    @Size(max = 100, message = "访问路径最多100字")
    private String path;

    /** 重定向地址 */
    @Size(max = 100, message = "重定向地址最多100字")
    private String redirect;

    /** 组件地址 */
    @Size(max = 50, message = "组件地址最多50字")
    private String component;

    /** 是否缓存 */
    private String keepAlive;

    /** 权限标识 */
    @NotBlank(message = "权限标识不能为空")
    @Size(max = 30, message = "权限标识最多30字")
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
    @Size(max = 20, message = "图标最多20字")
    private String icon;

    /** 菜单类型 */
    private String menuType;

    /** 排序编号 */
    @NotNull(message = "排序编号不能为空")
    @Min(value = 1, message = "排序编号低于最小值1")
    @Max(value = 999, message = "排序编号超出最大值999")
    private Integer orderNum;

    /** 状态 */
    private String status;

    /** 备注信息 */
    @Size(max = 250, message = "备注信息最多250字")
    private String remark;
}
