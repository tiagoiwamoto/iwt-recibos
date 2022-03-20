package br.com.tiagoiwamoto.iwtrecibos.config.filter;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/03/2022 | 06:21
 */

import br.com.tiagoiwamoto.iwtrecibos.core.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private static final String TOKEN_TYPE = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = "";
        String username = "";
        String authorizationHeader = request.getHeader("Authorization");

        if(this.isTokenPresent(authorizationHeader)){
            token = authorizationHeader.substring(TOKEN_TYPE.length());
            username = jwtService.getUsernameFromToken(token);
        }

        if(isUsernameNotInContext(username)){
            this.addUsernameInContext(request, username, token);
        }

        filterChain.doFilter(request, response);
    }

    private Boolean isTokenPresent(String authorizationHeader){
        return Objects.nonNull(authorizationHeader)  && authorizationHeader.startsWith(TOKEN_TYPE);
    }

    private Boolean isUsernameNotInContext(String username){
        return !username.isEmpty() && Objects.isNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private void addUsernameInContext(HttpServletRequest request, String username, String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
