package tk.yudady.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tk.yudady.event.MyEvent;

/**
 * 测试用注解监听器
 */
@Component
public class MyAnnotationListener {

	@EventListener
	public void listener1(MyEvent event) {
		System.out.println("注解事件监听-2：" + event.getMsg());
	}

	@EventListener
	public void listener2(MyEvent event) {
		System.out.println("注解事件监听-1：" + event.getMsg());
	}
}