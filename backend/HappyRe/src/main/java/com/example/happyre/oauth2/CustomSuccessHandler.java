package com.example.happyre.oauth2;

import com.example.happyre.dto.oauth.CustomOAuth2User;
import com.example.happyre.entity.UserEntity;
import com.example.happyre.jwt.JWTUtil;
import com.example.happyre.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;


    public CustomSuccessHandler(JWTUtil jwtUtil, UserRepository userRepository) {

        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String email = customUserDetails.getEmail();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();
        UserEntity user = userRepository.findByEmail(email);

        String token = jwtUtil.createJwt(email, role, 60 * 60 * 60 * 1000 * 500L, user.getId());
        System.out.println("Successfully OAuth2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1: " + email);
        response.addCookie(createCookie("Authorization", token));
        response.addHeader("Authorization", "Bearer " + token);
        if (user.getMyfrog() == null) {
            response.sendRedirect("https://i11b204.p.ssafy.io/usertest");
        } else {
            response.sendRedirect("https://i11b204.p.ssafy.io/profile");
        }

    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60 * 1000);
        //https 설정
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(false);

        return cookie;
    }
}
