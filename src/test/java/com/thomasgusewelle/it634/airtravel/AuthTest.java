package com.thomasgusewelle.it634.airtravel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class AuthTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TestPostSignUp() throws Exception {
        mockMvc.perform(post("/signup").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "test")
                        .param("email", "test@test.com")
                        .param("password", "password"))
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void TestPostLogIn() throws Exception {
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "test@test.com")
                        .param("password", "password"))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser
    public void TestDeleteUser() throws Exception {
        mockMvc.perform(post("/deleteUser").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "test")
                        .param("email", "test@test.com")
                        .param("password", "password"))
                .andExpect(redirectedUrl("/signup"));
    }
}
