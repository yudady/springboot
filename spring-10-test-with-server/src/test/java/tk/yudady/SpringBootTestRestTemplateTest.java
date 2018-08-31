package tk.yudady;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringBootTestRestTemplateTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testHome() {
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity("/", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("Hello World");
	}

	@Test
	public void testGet() {
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity("/users/1", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		String body = entity.getBody();
		Assert.assertNotNull(body);
		System.out.println(ReflectionToStringBuilder.toString(entity, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(body);

	}

	@Test
	public void testPost() {

		HttpHeaders headers = new HttpHeaders();
		// 请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		// 也支持中文
		params.add("id", "1");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
			params, headers);
		// 执行HTTP请求

		String url = "/users/1";
		ResponseEntity<String> entity = this.testRestTemplate.postForEntity(url, requestEntity, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

}
