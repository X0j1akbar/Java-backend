package uz.malga.logisticcompany.secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.malga.logisticcompany.entity.User;
import uz.malga.logisticcompany.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        User user = getUserFromRequest(httpServletRequest);
        if (user!=null&&user.isAccountNonExpired()
                &&user.isAccountNonLocked()
                &&user.isCredentialsNonExpired()
                &&user.isEnabled()){
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    public User getUserFromRequest(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth != null) {
            String token = auth.substring(7);
//            String token = auth;
            boolean validateToken = jwtProvider.validateToken(token);
            if (validateToken) {
                UUID uuid = UUID.fromString(jwtProvider.getUserIdFromToken(token));
                User user = userRepository.getById(uuid);
                return user;
            }

        }

        return null;
    }
}