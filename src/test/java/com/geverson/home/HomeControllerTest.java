package com.geverson.home;

import com.geverson.config.WebAppConfigurationAware;
import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class HomeControllerTest extends WebAppConfigurationAware {

    @Test
    public void shouldReturnIndexView() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("index"));
    }

}
