package br.com.devsuperior.dscommerce.services;

import br.com.devsuperior.dscommerce.dto.UserDto;
import br.com.devsuperior.dscommerce.entitites.Role;
import br.com.devsuperior.dscommerce.entitites.User;
import br.com.devsuperior.dscommerce.repositories.UserRepository;
import br.com.devsuperior.dscommerce.repositories.projections.UserDetailsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        List<UserDetailsProjection> projections = userRepository.searchUserAndRolesByEmail(email);
        if (projections.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(projections.get(0).getPassword());
        for (UserDetailsProjection projection : projections) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    protected User authenticate() {
        try {
            // essa chamada pega os claims do token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return userRepository.findByEmail(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Email not found");
        }
    }

    @Transactional(readOnly = true)
    public UserDto getMe() {
        User user = authenticate();
        return new UserDto(user);
    }
}
