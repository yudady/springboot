package com.tommy.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	String host;

	@Value("${spring.redis.port}")
	int port;

	@Value("${spring.redis.timeout}")
	int timeout;

	@Value("${spring.redis.password}")
	String password;

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		return new JedisPoolConfig();
	}

	@Bean
	public JedisPool getJedisPool() {
		return new JedisPool(jedisPoolConfig(), host, port, timeout, password);
	}

	@Bean
	public RedisStandaloneConfiguration redisStandaloneConfiguration() {
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
		standaloneConfig.setHostName(host);
		standaloneConfig.setPort(port);
		standaloneConfig.setPassword(RedisPassword.of(password));
		return standaloneConfig;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {

		// 单机配置
		RedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration());
		// 哨兵配置
		// RedisSentinelConfiguration

		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(redisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}
}
