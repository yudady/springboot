package tk.yudady.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.yudady.service.MusicService;
@RestController
public class MusicController {

	private static Logger log = LoggerFactory.getLogger(MusicController.class);

	@Autowired
	private MusicService musicService;

	@Autowired
	private CacheManager cacheManager;

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

		return "hello";
	}

	private void play(String instrument) {
		log.info("Calling: " + MusicService.class.getSimpleName() + ".play(\"" + instrument + "\");");
		musicService.play(instrument);
	}

}
