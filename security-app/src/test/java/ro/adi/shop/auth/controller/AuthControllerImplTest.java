package ro.adi.shop.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import ro.adi.shop.Urls;
import ro.adi.shop.auth.AuthDataProvider;
import ro.adi.shop.auth.service.AuthService;
import ro.adi.shop.users.service.UserServiceImpl;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureWebMvc
@SpringBootTest(classes = {AuthControllerImpl.class})
class AuthControllerImplTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AuthService authService;
    @MockBean
    UserServiceImpl userService;

    @Test
    void loginUser_ShouldSucceed() throws Exception {
        var request = AuthDataProvider.createUserLoginRequestMock();
        when(userService.loadUserByUsername(request.getUsername())).thenReturn(new User(request.getUsername(), request.getPassword(), Set.of()));

        mockMvc.perform(post(Urls.URL_AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(authService, times(1)).login(request);
    }
}