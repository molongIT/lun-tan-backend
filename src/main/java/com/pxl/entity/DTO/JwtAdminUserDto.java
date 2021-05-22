package com.pxl.entity.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pxl.entity.PO.AdminUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class JwtAdminUserDto implements UserDetails {

    private final AdminUser user;

    @JsonIgnore
    private List<GrantedAuthority> authorities;

    public Set<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return user.getEnabled();
    }

    @Override
    public String toString() {
        return "JwtAdminUserDto{" +
                "user=" + user +
                ", authorities=" + authorities +
                '}';
    }
}
