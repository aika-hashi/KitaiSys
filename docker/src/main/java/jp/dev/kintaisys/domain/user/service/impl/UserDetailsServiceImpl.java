package jp.dev.kintaisys.domain.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.dev.kintaisys.domain.user.model.MUser;
import jp.dev.kintaisys.domain.user.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        // ユーザー情報取得
        MUser loginUser = service.getLoginUser(loginId);

        // ユーザーが存在しない場合
        if(loginUser == null) {
            throw new UsernameNotFoundException("user not found");
        }

        // 権限List作成
        GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRoleId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        // UserDetails生成
        UserDetails userDetails = (UserDetails) new User(loginUser.getLoginId(), loginUser.getPassword(), authorities);

        return userDetails;
    }
}
