package com.soromiso.jwtex.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.soromiso.jwtex.repository.TokenRepository;
import com.soromiso.jwtex.service.JwtService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsService userDetailService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);  // "Bearer " 7자를 제외한 문자열을 가져옵니다.
        userEmail = jwtService.extractUsername(jwt); // jwt에서 extractUsername(userEmail) 추출합니다.

        System.out.println("🍎49: jwt: " + jwt);
        System.out.println("🍎50: userEmail: " + userEmail);

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // userEmail이 null이 아니고, SecurityContextHolder.getContext().getAuthentication()이 null이면
            // 즉, 아직 인증이 되지 않았다면
            // 인증을 시도합니다.

            System.out.println("🍎63: User is InValid");

            UserDetails userDetails = this.userDetailService.loadUserByUsername(userEmail);
            var isTokenValid = tokenRepository.findByToken(jwt)
                                                .map(t -> !t.isExpired() && !t.isRevoked())
                                                .orElse(false);

            System.out.println("🍎70: isTokenValid: " + isTokenValid);
            System.out.println("🍎71: userDetails" + userDetails);

            if(jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                // jwt가 유효하다면
                System.out.println("🍎: 75 jwt is valid");

                // username password authentication token을 만들어서
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, 
                    null, 
                    userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // SecurityContextHolder에 인증을 저장합니다.
                SecurityContextHolder.getContext()
                                     .setAuthentication(authToken);
            } else {
                System.out.println("🍎: 76 jwt is not valid");
            }
        } else {
            System.out.println("🍎: 79 Required authorizing");
        }

        filterChain.doFilter(request, response);

    }
    
}
