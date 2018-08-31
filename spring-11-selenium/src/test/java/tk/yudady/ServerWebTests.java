package tk.yudady;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerWebTests {

	private static ChromeDriver browser;

	@Value("${local.server.port}")
	private int port;

	@Autowired
	ResourceLoader resourceLoader;

	@BeforeClass
	public static void beforeClass() throws IOException {
		System.setProperty("webdriver.chrome.driver",
			"D:\\tommy\\springboot\\spring-11-selenium\\src\\main\\resources\\chromedriver.exe");

	}

	@Before
	public void openBrowser() throws IOException {

		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void closeBrowser() {
		browser.quit();
	}

	@Test
	public void addBookToEmptyList() throws InterruptedException {
		System.out.println("服务器端口:" + port);
		// 要测试的网址
		String baseUrl = "http://localhost:" + port + "/TEST";
		browser.get(baseUrl);
		// 对网页表单元素进行赋值操作并提交表单
		Assert.assertEquals("You have no books in your book list",
			browser.findElementByTagName("div").getText());
		browser.findElementByName("title").sendKeys("BOOK TITLE");
		browser.findElementByName("author").sendKeys("BOOK AUTHOR");
		browser.findElementByName("isbn").sendKeys("1234567890");
		browser.findElementByName("description").sendKeys("DESCRIPTION");
		browser.findElementByTagName("form").submit();
		// 测试运行结果是否符合预期
		WebElement dl = browser.findElementByCssSelector("dt.bookHeadline");
		Assert.assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", dl.getText());
		WebElement dt = browser.findElementByCssSelector("dd.bookDescription");
		Assert.assertEquals("DESCRIPTION", dt.getText());
	}

}