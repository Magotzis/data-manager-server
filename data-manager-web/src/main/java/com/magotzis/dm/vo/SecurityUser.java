package com.magotzis.dm.vo;

import com.magotzis.dm.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dengyq on 14:23 2018/1/17
 */
public class SecurityUser extends User implements UserDetails {

    public SecurityUser(User user) {
        BeanUtils.copyProperties(user, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getRoles())) {
            this.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
