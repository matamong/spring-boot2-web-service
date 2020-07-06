package com.gameduos.springboot.web.config;

import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.yml", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    protected MockHttpSession session;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    public void startSession(Role role) {
        session = new MockHttpSession();

        session.setAttribute("user", User.builder()
                .nickName("test")
                .id(new Long(1))
                .role(role)
                .build());
    }

    public void endSession() {
        session.clearAttributes();
        session = null;
    }


    @Test
    public void 로그인_없이_home_으로_가면_login_창이_뜬다() throws Exception {
        mvc.perform(get("/home").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "GuestUser", roles = "GUEST")
    public void 게스트유저는_Home에_접근이_가능하다() throws Exception {
        startSession(Role.GUEST);
        mvc.perform(get("/home")
                .session(session)
                .with(csrf()))
                .andExpect(status().isOk());
        endSession();
    }

    @Test
    @WithMockUser(username = "GuestUser", roles = "GUEST")
    public void 게스트유저는_마이페이지에_접근이_가능하다() throws Exception {
        startSession(Role.GUEST);
        mvc.perform(get("/myPage")
                .session(session)
                .with(csrf()))
                .andExpect(status().isOk());
        endSession();
    }

    @Test
    @WithMockUser(username = "GuestUser", roles = "GUEST")
    public void 게스트유저는_게시판에_접근이_금지된다() throws Exception {
        startSession(Role.GUEST);
        mvc.perform(get("/board/list")
                .session(session)
                .with(csrf()))
                .andExpect(status().isForbidden());
        endSession();
    }

    @Test
    @WithMockUser(username = "GuestUser", roles = "GUEST")
    public void 게스트유저는_관리자페이지에_접근이_금지된다() throws Exception {
        startSession(Role.GUEST);
        mvc.perform(get("/admin")
                .session(session)
                .with(csrf()))
                .andExpect(status().isForbidden());
        endSession();
    }


    @Test
    @WithMockUser(username = "GuestUser", roles = "GUEST")
    public void 게스트유저는_마스터페이지에_접근이_금지된다() throws Exception {
        startSession(Role.GUEST);
        mvc.perform(get("/master")
                .session(session)
                .with(csrf()))
                .andExpect(status().isForbidden());
        endSession();
    }

    @Test
    @WithMockUser(username = "User", roles = "USER")
    public void 유저는_게시판에_접근이_가능하다() throws Exception {
        startSession(Role.USER);
        mvc.perform(get("/board/list")
                .session(session)
                .with(csrf()))
                .andExpect(status().isOk());
        endSession();
    }

    @Test
    @WithMockUser(username = "User", roles = "USER")
    public void 유저는_관리자페이지에_접근이_금지된다() throws Exception {
        startSession(Role.USER);
        mvc.perform(get("/admin")
                .session(session)
                .with(csrf()))
                .andExpect(status().isForbidden());
        endSession();
    }

    @Test
    @WithMockUser(username = "User", roles = "USER")
    public void 유저는_마스터페이지에_접근이_금지된다() throws Exception {
        startSession(Role.USER);
        mvc.perform(get("/master")
                .session(session)
                .with(csrf()))
                .andExpect(status().isForbidden());
        endSession();
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void 관리자는_관리자페이지에_접근이_가능하다() throws Exception {
        startSession(Role.USER);
        mvc.perform(get("/admin")
                .session(session)
                .with(csrf()))
                .andExpect(status().isOk());
        endSession();
    }
    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void 관리자는_게시판에_접근이_가능하다() throws Exception {
        startSession(Role.USER);
        mvc.perform(get("/board/list")
                .session(session)
                .with(csrf()))
                .andExpect(status().isOk());
        endSession();
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    public void 관리자는_마스터페이지에_접근이_금지된다() throws Exception {
        startSession(Role.USER);
        mvc.perform(get("/master")
                .session(session)
                .with(csrf()))
                .andExpect(status().isForbidden());
        endSession();
    }


    @Test
    @WithMockUser(username = "master", roles = "MASTER")
    public void 마스터는_관리자페이지에_접근이_가능하다() throws Exception {
        startSession(Role.MASTER);
        mvc.perform(get("/admin")
                .session(session)
                .with(csrf()))
                .andExpect(status().isOk());
        endSession();
    }
    
    @Test
    @WithMockUser(username = "master", roles = "MASTER")
    public void 마스터는_마스터페이지에_접근이_가능하다() throws Exception {
        startSession(Role.MASTER);
        mvc.perform(get("/master")
                .session(session)
                .with(csrf()))
                .andExpect(status().is4xxClientError());
        endSession();
    }

}
