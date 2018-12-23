package com.tommy.kafka;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@EnableScheduling
public class KafkaProductor {

	@Autowired
	private KafkaTemplate kafkaTemplate;

	@Scheduled(cron = "00/1 * * * * ?")
	public void send() {
		String msg = UUID.randomUUID().toString();
		ListenableFuture future = kafkaTemplate.send("uuid", msg);

		future.addCallback((success) -> {
			System.out.println("success : " + success);
		}, (fail) -> {
			System.out.println("fail : " + fail);
		});
	}
}
