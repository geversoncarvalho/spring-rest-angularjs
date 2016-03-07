package com.geverson.config;

import org.junit.Before;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.util.Base64Utils;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public abstract class WebSecurityConfigurationAware extends WebAppConfigurationAware {

    @Inject
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }

    public String getCredentials(String username, String password) {
        String encodeStr = username + ":" + password;
        return "Basic " + Base64Utils.encodeToString(encodeStr.getBytes());
    }
}
