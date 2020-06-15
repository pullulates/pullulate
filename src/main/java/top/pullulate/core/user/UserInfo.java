/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package top.pullulate.core.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import top.pullulate.system.entity.PulDept;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.entity.PulRole;
import top.pullulate.system.entity.PulUser;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserInfo extends User {

    /** 用户主键 */
    private String userId;

    /** 会话凭证 */
    private String token;

    /** 登陆时间 */
    private LocalDateTime loginTime;

    /** 过期时间 */
    private LocalDateTime ExpireTime;

    /** IP地址 */
    private String ip;

    /** 地理位置 */
    private String location;

    /** 浏览器 */
    private String browser;

    /** 客户端 */
    private String os;

    /** 用户基础信息 */
    private PulUser user;

    /** 用户部门信息 */
    private PulDept dept;

    /** 所属角色信息 */
    private List<PulRole> roles;

    /** 拥有菜单集合 */
    private List<PulMenu> menus;

    /** 权限集合 */
    private Set<String> permissions;

    public UserInfo(String userId, PulUser user, PulDept dept, List<PulRole> roles, List<PulMenu> menus, Set<String> permissions, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.user = user;
        this.dept = dept;
        this.roles = roles;
        this.permissions = permissions;
    }
}
