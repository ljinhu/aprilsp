package com.jimbolix.april.authorization.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @Description
 * @ClassName AprilUserDetailService
 * @Author liruihui
 * @date 2020.05.10 20:38
 */
@Service(value = "aprilUserDetailService")
@Slf4j
public class AprilUserDetailService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("@@@@加载用户信息@@@@");
        return new User("li",
        "$2a$10$wY41ztzpHE4EeSypL7/jEOK1L/Sa4Wlm2keQxzSCTYPLIn/nDfvmy",
        true,
        true,
        true,
        true,
        new HashSet<GrantedAuthority>());
    }
}
