package com.geverson.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.geverson.config.WebSecurityConfigurationAware;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.Base64Utils;

@Profile("test")
public class UserAuthenticationIntegrationTest extends WebSecurityConfigurationAware {

    private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

    @Ignore
    @Test
    public void requiresAuthentication() throws Exception {
        mockMvc.perform(get("/api/account/current"))
                .andExpect(redirectedUrl("http://localhost/signin"));
    }

    @Test
    public void shouldRespondeUnauthorized() throws Exception {
        mockMvc.perform(get("/api/account/current"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void userAuthenticates() throws Exception {
        final String username = "user";
        String password = "demo";

        ResultMatcher matcher = new ResultMatcher() {
            public void match(MvcResult mvcResult) throws Exception {
                HttpSession session = mvcResult.getRequest().getSession();
                SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                Assert.assertEquals(securityContext.getAuthentication().getName(), username);
            }
        };

        mockMvc.perform(get("/api/account/current").header("Authorization", getCredentials(username, password)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void userAuthenticationFails() throws Exception {
        final String username = "user";
        String password = "wrong";
        mockMvc.perform(get("/api/account/current").header("Authorization", getCredentials(username, password)))
                .andExpect(status().isUnauthorized());
    }
}
