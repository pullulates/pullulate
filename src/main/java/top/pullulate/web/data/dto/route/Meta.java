package top.pullulate.web.data.dto.route;

import lombok.Getter;
import lombok.Setter;

/**
 * @功能描述: 路由显示信息
 * @Author: xuyong
 * @Date: 2020/6/18 17:12
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Setter
@Getter
public class Meta {

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 英文名字
     */
    private String usTitle;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;

    /**
     * 与项目提供的权限拦截匹配的权限，如果不匹配，则会被禁止访问该路由页面
     */
    private String[] permission;

    /**
     * 缓存该路由 (开启 multi-tab 是默认值为 true)
     */
    private Boolean keepAlive;

    /**
     * *特殊 隐藏 PageHeader 组件中的页面带的 面包屑和页面标题栏
     */
    private Boolean hiddenHeaderContent;

    /**
     * 是否显示
     */
    private Boolean show;

    /**
     * 是否本窗口打开
     */
    private String target;

    public Meta() {
    }

    public Meta(String title, String usTitle, String icon, String[] permission, Boolean keepAlive, Boolean hiddenHeaderContent, Boolean show, String target) {
        this.title = title;
        this.usTitle = usTitle;
        this.icon = icon;
        this.permission = permission;
        this.keepAlive = keepAlive;
        this.hiddenHeaderContent = hiddenHeaderContent;
        this.show = show;
        this.target = target;
    }
}
