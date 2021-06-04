package az.yeich.security;

import az.yeich.constant.HttpRequestHeaderConstants;
import az.yeich.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthRequestFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer";

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        getAuthentication(request).ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));
        filterChain.doFilter(request, response);
    }

    public Optional<Authentication> getAuthentication(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader(HttpRequestHeaderConstants.AUTHORIZATION))
                .filter(this::isBearerAuth)
                .flatMap(this::getAuthenticationBearer);
    }

    private boolean isBearerAuth(String header) {
        return header.toLowerCase().startsWith(TOKEN_PREFIX.toLowerCase());
    }

    private Optional<Authentication> getAuthenticationBearer(String header) {
        String token = header.substring(TOKEN_PREFIX.length()).trim();
        Claims claims = jwtUtil.parseToken(token);
        return Optional.of(getAuthenticationBearer(claims));
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        JwtUser jwtUser = JwtUser.builder()
                .id(claims.getId())
                .firstname(claims.getSubject())
                .email(claims.get("email", String.class))
                .build();

        return new UsernamePasswordAuthenticationToken(jwtUser, "", null);
    }


}




