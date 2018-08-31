package tk.yudady;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tk.yudady.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Mockito.mock(UserService.class);    //<==
	}

	@Test
	public void whenQueryUserSuccess() throws Exception {
		String url = "/user";

		/**
		 * perform
		 *
		 *
		 * MockMvcRequestBuilders
		 */
		ResultActions perform = mockMvc
			.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8));

		// ----------------------------

		/**
		 * andExpect
		 * 
		 * 
		 * MockMvcResultMatchers
		 */
		String s = perform.andExpect(MockMvcResultMatchers.status().isOk()) //
			// .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
			.andReturn().getResponse().toString()//
		;
		System.out.println(s);
	}
}
