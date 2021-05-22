package com.pxl.common.security;

import com.pxl.common.exception.BadRequestException;
import com.pxl.common.exception.EntityNotFoundException;
import com.pxl.entity.PO.AdminUser;
import com.pxl.entity.DTO.JwtAdminUserDto;
import com.pxl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser user;
        try {
            user = userService.findByName(username);
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("", e);
        }
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        if (!user.getEnabled()) {
            throw new BadRequestException("账号未激活");
        }
        if (user.getIsRoot()) {
            return new JwtAdminUserDto(user, AuthorityUtils.createAuthorityList("root"));
        }
        return new JwtAdminUserDto(user, AuthorityUtils.createAuthorityList("admin"));
    }

}
