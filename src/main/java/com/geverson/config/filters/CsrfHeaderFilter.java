package com.geverson.config.filters;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrf = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());
        if(csrf != null){
            Cookie cookie = WebUtils.getCookie(httpServletRequest, "XSRF-TOKEN");
            String token = csrf.getToken();
            if(cookie == null || token != null && !token.equals(cookie.getValue())){
                cookie = new Cookie("XSRF-TOKEN", token);
                cookie.setPath("/");
                httpServletResponse.addCookie(cookie);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
