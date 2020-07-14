package top.pullulate.web.data.dto.route;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @功能描述:   前端路由封装类
 * @Author: xuyong
 * @Date: 2020/6/18 17:11
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Getter
@Setter
public class Router {

    /**
     * 路由名称, 必须设置,且不能重名
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 控制路由和子路由是否显示在 sidebar
     */
    private Boolean hidden;

    /**
     * 重定向地址, 访问这个路由时,自定进行重定向
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 路由元信息（路由附带扩展信息）
     */
    private Meta meta;

    /**
     * 强制菜单显示为Item而不是SubItem(配合 meta.hidden)
     */
    private Boolean hideChildrenInMenu;

    /**
     * 子路由
     */
    private List<Router> children;

    public Router() {
    }

    public Router(String name, String path, Boolean hidden, String redirect, String component, Meta meta, Boolean hideChildrenInMenu) {
        this.name = name;
        this.path = path;
        this.hidden = hidden;
        this.redirect = redirect;
        this.component = component;
        this.meta = meta;
        this.hideChildrenInMenu = hideChildrenInMenu;
    }
}
