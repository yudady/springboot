package com.tommy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <pre>
 *
 *
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class) //
@SpringBootTest(classes = App.class)
public class RedisTemplate01 {

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	public void testRedisTemplate01() throws Exception {
		redisTemplate.opsForValue().set("testRedisTemplate01", "testRedisTemplate01");

	}
}