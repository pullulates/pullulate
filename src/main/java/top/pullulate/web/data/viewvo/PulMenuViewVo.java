package top.pullulate.web.data.viewvo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

/**
 * @功能描述:   菜单视图展现类
 * @Author: xuyong
 * @Date: 2020/7/9 8:27
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulMenuViewVo implements Comparable<PulMenuViewVo> {

    /** 菜单主键 */
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

    /** 备注信息 */
    private String remark;

    /** 子菜单 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PulMenuViewVo> children;

    @Override
    public int compareTo(PulMenuViewVo o) {
        int i = Integer.valueOf(this.getMenuType()) - Integer.valueOf(o.getMenuType());
        if(i == 0){
            return this.getOrderNum() - o.getOrderNum();
        }
        return i;
    }
}
