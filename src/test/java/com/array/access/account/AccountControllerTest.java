package com.array.access.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import javax.annotation.Resource;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.array.access.account.dao.UserRepository;
import com.array.access.account.model.User;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	private MockMvc mvc;

	@Resource
	private UserRepository userRepository;

	@Test
	public void login() throws Exception {

		// Invalid user account
		mvc.perform(
				MockMvcRequestBuilders.post("/login").flashAttr("user", new User()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", equalTo("Invalid user account provided")))
				.andExpect(view().name("index"));

		// Auto register credentials
		mvc.perform(MockMvcRequestBuilders.post("/login").flashAttr("user", new User("test@email.com", "testPassword"))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(model().attribute("user", Matchers.hasProperty("email", Matchers.equalTo("test@email.com"))))
				.andExpect(
						model().attribute("user", Matchers.hasProperty("password", Matchers.equalTo("testPassword"))))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", equalTo("(online - auto registered)")))
				.andExpect(view().name("members"));

		// Login with credentials
		mvc.perform(MockMvcRequestBuilders.post("/login").flashAttr("user", new User("test@email.com", "testPassword"))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(model().attribute("user", Matchers.hasProperty("email", Matchers.equalTo("test@email.com"))))
				.andExpect(
						model().attribute("user", Matchers.hasProperty("password", Matchers.equalTo("testPassword"))))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", equalTo("(online)"))).andExpect(view().name("members"));

		// Invalid password
		mvc.perform(MockMvcRequestBuilders.post("/login").flashAttr("user", new User("test@email.com", "testyPassword"))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", equalTo("Invalid credentials")))
				.andExpect(view().name("index"));

	}

	@Test
	public void logout() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/logout")
				.flashAttr("user", new User("test@email.com", "testyPassword")).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(model().attributeExists("user"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", equalTo("Successful logoff test@email.com")))
				.andExpect(view().name("index"));
	}
	
	@Test
    public void insertRetrieveUser() {
        User user = new User("email", "password");
        userRepository.save(user);
        
        List<User> user2 = userRepository.findByEmail(user.getEmail());
        assertThat(user2.get(0).getEmail()).isEqualTo(user.getEmail());
        assertThat(user2.get(0).getPassword()).isEqualTo(user.getPassword());
      }
}
