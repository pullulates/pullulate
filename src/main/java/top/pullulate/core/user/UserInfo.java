package top.pullulate.core.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.entity.PulRole;
import top.pullulate.system.entity.PulUser;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @功能描述: 登录用户信息
 * @Author: pullulates
 * @Date: 2020/6/10 0010 12:49
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
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

    /** 所属角色信息 */
    private List<PulRole> roles;

    /** 拥有菜单集合 */
    private List<PulMenu> menus;

    /** 权限集合 */
    private Set<String> permissions;

    public UserInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
