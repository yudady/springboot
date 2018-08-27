package tk.yudady.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.yudady.service.EhcacheService;
import tk.yudady.service.MusicService;
@RestController
public class MusicController {

	private static Logger log = LoggerFactory.getLogger(MusicController.class);

	@Autowired
	private MusicService musicService;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private EhcacheService ehcacheService;



	@GetMapping("/")
	public Object index() throws Exception {
		log.info("Spring Boot Ehcache 2 Caching Example Configuration");
		log.info("using cache manager: " + cacheManager.getClass().getName());

		musicService.clearCache();

		play("trombone");
		play("guitar");
		play("trombone");
		play("bass");
		play("trombone");
		play("trombone");
		play("trombone");
		play("trombone");
		play("trombone");
		play("trombone");





		System.out.println("第一次调用：" + ehcacheService.getTimestamp("param"));
		Thread.sleep(2000);
		System.out.println("2秒之后调用：" + ehcacheService.getTimestamp("param"));
		Thread.sleep(4000);
		System.out.println("再过4秒之后调用：" + ehcacheService.getTimestamp("param"));


		String key = "mengdee";
		ehcacheService.refreshData(key);  // 模拟从数据库中加载数据
		String data = ehcacheService.getDataFromDB(key);
		System.out.println("data:" + data); // data:mengdee::103385

		ehcacheService.refreshData(key);  // 模拟从数据库中加载数据
		String data2 = ehcacheService.getDataFromDB(key);
		System.out.println("data2:" + data2);   // data2:mengdee::180538







		ehcacheService.findById("1"); // 模拟从数据库中查询数据
		ehcacheService.findById("1");








		ehcacheService.isReserved("123");
		ehcacheService.isReserved("123");








		// 线添加到缓存
		ehcacheService.findById("1");

		// 再删除
		ehcacheService.removeUser("1");

		// 如果不存在会执行方法体
		ehcacheService.findById("1");








		ehcacheService.findById("1");
		ehcacheService.findById("2");

		ehcacheService.removeAllUser();

		ehcacheService.findById("1");
		ehcacheService.findById("2");

		//      模拟从数据库中查询数据
		//      模拟从数据库中查询数据
		//      UserCache delete all
		//      模拟从数据库中查询数据
		//      模拟从数据库中查询数据

		return "hello";
	}

	private void play(String instrument) {
		log.info("Calling: " + MusicService.class.getSimpleName() + ".play(\"" + instrument + "\");");
		musicService.play(instrument);
	}

}
