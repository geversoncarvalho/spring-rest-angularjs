package com.geverson.signup;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.geverson.config.WebAppConfigurationAware;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class SignupControllerTest extends WebAppConfigurationAware {

    @Test
    public void displaysSignupForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(view().name("signup.signup"))
                .andExpect(model().attributeExists("signupForm"))
                .andExpect(status().isOk());
                /*.andExpect(content().string(
                        allOf(containsString("<legend>Please Sign Up</legend>"))));*/
    }

    @Test
    public void shouldReturnErrors() throws Exception {
        mockMvc.perform(post("/signup")
                .param("email", "")
                .param("password", ""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("signup.signup"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("signupForm", "email", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("signupForm", "password", "NotBlank"));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        mockMvc.perform(post("/signup").param("email", "test@test.com").param("password", "1234"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("redirect:/"))
                .andExpect(model().hasNoErrors());
    }
}