package com.demo.web.security;

import com.demo.model.Account;
import com.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (new EmailValidator().isValid(username, null)){
            return accountRepository
                    .findOneWithAuthoritiesByEmail(username)
                    .map(account -> createUserSecurity(account))
                    .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' is not exist in system"));
        }

        return accountRepository
                .findOneWithAuthoritiesByUsername(username)
                .map(account -> createUserSecurity(account))
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' is not exist in system"));

    }

    private org.springframework.security.core.userdetails.User createUserSecurity(Account account){
        Set<GrantedAuthority> securityAuthorities = account
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), securityAuthorities);

    }

}
