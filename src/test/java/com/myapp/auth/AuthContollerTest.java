package com.myapp.auth;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myapp.auth.model.User;
import com.myapp.auth.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

class LoginRequest{
    public String usernameorEmail;
    public String password;
}
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUserWithMockRepository() throws Exception {
        Optional<User> optUser = Optional.of(new User("New User", "testuser", "test_email@email.com", "password"));
        when(userRepository.findByUsername("testuser")).thenReturn(optUser);
    }

    public void testSignupUser() throws Exception {
        String url = "/api/auth/signup";
        Optional<User> user = Optional.of( new User("New User", "testuser2", "test_email2@email.com", "password"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);
        mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)).andExpect(status().isOk());
    }

    public void testSigninUser() throws Exception {
        String url = "/api/auth/signin";
        LoginRequest userRequest = new LoginRequest();
        userRequest.usernameorEmail = "testuser2";
        userRequest.password = "password";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(userRequest);
        mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)).andExpect(status().isOk());
    }
}