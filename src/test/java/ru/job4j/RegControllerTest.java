package ru.job4j;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.model.User;
import ru.job4j.service.AuthorityService;
import ru.job4j.service.UserService;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RegControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthorityService authorityService;


    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage()  throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("regPage"))
                .andExpect(model().attribute("errMessage", is(nullValue())));
    }

    @Test
    @WithMockUser
    public void shouldReturnErrorMessage()  throws Exception {
        this.mockMvc.perform(get("/reg")
                        .param("fail", "true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("regPage"))
                .andExpect(model().attribute("errMessage", is("User with this username already exists!")));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageSave() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "Sergei")
                        .param("password", "123456"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userService).save(argument.capture());
        assertThat(argument.getValue().getUsername(), is("Sergei"));
    }
}
