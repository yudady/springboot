package tk.yudady;

import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.client.RestTemplate;

import tk.yudady.pojo.Name;
import tk.yudady.pojo.NameView;

public class PostForObjectDemo {

	public static void main(String args[]) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		Name name = new Name("Arvind", "Kumar", "Rai");
		MappingJacksonValue jacksonValue = new MappingJacksonValue(name);
		jacksonValue.setSerializationView(NameView.FirstLastName.class);
		HttpEntity<MappingJacksonValue> entity = new HttpEntity<MappingJacksonValue>(jacksonValue, headers);

		//
		String url = "http://localhost:8080/app/familyprofile";
		RestTemplate restTemplate = new RestTemplate();
		String output = restTemplate.postForObject(url, entity, String.class);

		System.out.println("Name:" + output);

	}
}
