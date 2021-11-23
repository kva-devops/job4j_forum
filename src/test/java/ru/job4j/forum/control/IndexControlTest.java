package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class IndexControlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private CommentService commentService;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void shouldReturnLoginPage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    public void shouldReturnCreatePage() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/create"));
    }

    @Test
    @WithMockUser
    public void shouldReturnUpdatePage() throws Exception {
        this.mockMvc.perform(get("/update?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/update"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPostPage() throws Exception {
        this.mockMvc.perform(get("/post?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnNewItem() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "name 1")
                .param("description", "some description 1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argumentCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postService).addPostToStore(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getName(), is("name 1"));
        assertThat(argumentCaptor.getValue().getDescription(), is("some description 1"));
    }

    @Test
    @WithMockUser
    public void shouldReturnNewComment() throws Exception {
        this.mockMvc.perform(post("/comment/save")
                .param("post.id", "1")
                .param("description", "desc 1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Comment> argumentCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(commentService).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getDescription(), is("desc 1"));
    }
}
