package com.liverpool.configuration.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.configuration.beans.LoginPayload;
import com.liverpool.configuration.beans.UserResponse;
import com.liverpool.configuration.beans.Users;
import com.liverpool.configuration.controller.UserController;
import com.liverpool.configuration.service.UserService;

 

@SpringBootTest
public class UserControllerTest {

 

    //private static String USER_URL = "/ user";
    
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    private UserController userController;
    
    private ObjectMapper objectMapper;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }
    
    @Test
       public void getUsers() throws Exception {
           when(userService.getUsers()).thenReturn(new Users());
           mockMvc.perform(
                   get("/user")
                   .contentType(MediaType.APPLICATION_JSON)
                   ).andExpect(status().isOk());
       }
    @Test
    public void register()  throws Exception {
         when(userService.addUser(null)).thenReturn("registered");
           mockMvc.perform(
                   post("/register")
                   .content("{\"userName\": \"raki\",\"password\": \"raki123\",\"firstName\": \"raki\",\"lastName\": \"surapanani\",\"email\": \"raki@gmail.com\",\"roles\":[{ \"id\":\"raki1\",\"roleName\":\"admin\"}]}")
                   .contentType(MediaType.APPLICATION_JSON)
                   ).andExpect(status().isOk());    
    }
    @Test
    public void login() throws Exception {
         when(userController.login(any(LoginPayload.class))).thenReturn(new UserResponse());
           mockMvc.perform(
                   post("/login")
                   .content("{\"userName\":\"raki\",\"password\":\"raki123\"}")
                   .contentType(MediaType.APPLICATION_JSON)
                   ).andExpect(status().isOk());    
    }
}