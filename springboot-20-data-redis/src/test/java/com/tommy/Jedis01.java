package com.tommy;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <pre>
 *
 *
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class) //
@SpringBootTest(classes = App.class)
public class Jedis01 {

	@Autowired
	JedisPool jedisPool;

	@Test
	public void init() throws Exception {
		System.out.println(jedisPool.getResource());
	}

	@Test
	public void testJedis01() throws Exception {
		Jedis resource = jedisPool.getResource();

		String set = resource.set("key1", "" + new Date().getTime());
		System.out.println(set);
		resource.close();

	}
}