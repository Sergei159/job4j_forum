package ru.job4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.model.Post;
import ru.job4j.service.PostService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessagePostInfo() throws Exception {
        when(postService.findById(1)).thenReturn(Post.of("post"));
        mockMvc.perform(get("/postInfo/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("postInfo"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageEdit() throws Exception {
        when(postService.findById(1)).thenReturn(Post.of("post"));
        mockMvc.perform(get("/edit/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAddPost() throws Exception {
        mockMvc.perform(get("/addPost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addPost"));
    }
    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageDelete() throws Exception {
        when(postService.findById(1)).thenReturn(Post.of("post"));
        mockMvc.perform(get("/deletePost/{postId}", "1"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/index"));
    }
}
