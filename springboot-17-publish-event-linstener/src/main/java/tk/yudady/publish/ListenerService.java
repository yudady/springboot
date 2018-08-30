package tk.yudady.publish;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tk.yudady.event.MyEvent;
@Service("listenerService")
public class ListenerService {

	/**
	 * 上下文对象
	 */
	@Resource
	private ApplicationContext applicationContext;

	/**
	 *
	 * @param msg
	 */
	public void publish(String msg) {

		System.out.println("通过spring上下文对象发布监听 -> MyEvent " + msg);
		applicationContext.publishEvent(new MyEvent(this, msg));

	}
}