package com.tommy.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
public class WebAsyncTaskController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 带超时时间的异步请求 通过WebAsyncTask自定义客户端超时间
	 *
	 * 这种方式和上面的callable方式最大的区别就是，WebAsyncTask支持超时，
	 * 
	 * 并且还提供了两个回调函数，分别是onCompletion和onTimeout，
	 * 
	 * 顾名思义，这两个回调函数分别在执行完成和超时的时候回调。
	 *
	 * 
	 * @return
	 */
	@GetMapping("/webAsyncTask")
	public WebAsyncTask<String> worldController() {
		logger.info(Thread.currentThread().getName() + " 进入helloController方法");

		// 3s钟没返回，则认为超时
		WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info(Thread.currentThread().getName() + " 进入call方法");
				TimeUnit.SECONDS.sleep(2);
				logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
				return "WebAsyncTask";
			}
		});
		logger.info(Thread.currentThread().getName() + " 从helloController方法返回");

		webAsyncTask.onCompletion(new Runnable() {

			@Override
			public void run() {
				logger.info(Thread.currentThread().getName() + " 执行完毕");
			}
		});

		webAsyncTask.onTimeout(new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info(Thread.currentThread().getName() + " onTimeout");
				// 超时的时候，直接抛异常，让外层统一处理超时异常
				throw new TimeoutException("调用超时");
			}
		});
		return webAsyncTask;
	}

	/**
	 * 异步调用，异常处理，详细的处理流程见MyExceptionHandler类
	 *
	 * @return
	 */
	@GetMapping("/exception")
	public WebAsyncTask<String> exceptionController() {
		logger.info(Thread.currentThread().getName() + " 进入helloController方法");
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info(Thread.currentThread().getName() + " 进入call方法");
				throw new TimeoutException("调用超时!");
			}
		};
		logger.info(Thread.currentThread().getName() + " 从helloController方法返回");
		return new WebAsyncTask<>(30_000, callable);
	}
}
