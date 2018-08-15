package tk.tommy.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {

	@Autowired
	WebApplicationContext wac;

	@Autowired
	RestTemplate restTemplate;

	MockMvc mockMvc;

	// @Before
	// public void before() {
	// mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	// }

	@Test
	public void test01() {
		Assert.assertNotNull(wac);
		Assert.assertNotNull(mockMvc);
	}

	@Test
	public void test02() {
		Assert.assertNotNull(restTemplate);


		RestTemplate restTemplate2 = new RestTemplate();

		String forObject = restTemplate2.getForObject("http://www.google.com", String.class, 200);
		System.out.println(forObject);
	}
}