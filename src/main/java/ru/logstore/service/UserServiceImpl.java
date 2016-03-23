package ru.logstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.logstore.model.User;
import ru.logstore.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Service("userService")
public class UserServiceImpl implements UserService, AuthenticationProvider {

    @Autowired
    private UserRepository repository;

    public User get(int id){
        return repository.get(id);
    }

    public User getByName(String name) {
        return repository.getByName(name);
    }

    public String getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof String) ? (String) user : null;   }

    @Override
    public User getByNameAndPass(String name, String pass) {
        return repository.getByNameAndPass(name, pass);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = repository.getByNameAndPass(name, password);
        if (user == null) {
            name = "NotAuthorised";
            password = "";
        }
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
