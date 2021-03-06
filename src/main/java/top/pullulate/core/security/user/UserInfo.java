package top.pullulate.core.security.user;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.pullulate.common.constants.Constant;
import top.pullulate.common.enums.LockFlag;
import top.pullulate.system.entity.PulDept;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.entity.PulRole;
import top.pullulate.system.entity.PulUser;
import top.pullulate.web.data.viewvo.system.PulDictDataViewVo;
import top.pullulate.web.data.dto.route.Router;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo implements UserDetails {

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
    private List<Router> routers;

    /** 权限集合 */
    private Set<String> permissions;

    /** 字典集合 */
    private Map<String, List<PulDictDataViewVo>> dicts;

    public UserInfo(String userId, PulUser user) {
        this.userId = userId;
        this.user = user;
    }

    public UserInfo(String userId, PulUser user, PulDept dept, List<PulRole> roles, List<PulMenu> menus, Set<String> permissions) {
        this.userId = userId;
        this.user = user;
        this.dept = dept;
        this.roles = roles;
        this.permissions = permissions;
    }

    public Boolean willSuperman() {
        return roles.stream().anyMatch(role -> Constant.ROLE_KEY_SUPREMAN.equals(role.getRoleKey()));
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return CollectionUtil.isEmpty(permissions) ? null : permissions.stream()
                .map(perm -> new SimpleGrantedAuthority(perm))
                .collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !LockFlag.hasLocked(user.getLockFlag());
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return !LockFlag.hasLocked(user.getLockFlag());
    }
}
