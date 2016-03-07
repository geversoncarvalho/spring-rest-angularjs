package com.geverson.people;

import com.geverson.config.WebSecurityConfigurationAware;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class PeopleControllerTest extends WebSecurityConfigurationAware {

    @Test
    public void shouldNotPermitAccess() throws Exception {
        mockMvc.perform(get("/api/people"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnPeopleList() throws Exception {
        String user = "user";
        String password = "demo";
        mockMvc.perform(get("/api/people").header("Authorization", getCredentials(user, password)))
                .andExpect(status().isOk());
    }

}
