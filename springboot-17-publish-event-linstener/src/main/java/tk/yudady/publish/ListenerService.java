package tk.yudady.publish;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import tk.yudady.event.MyEvent;
@Service("listenerService")
public class ListenerService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
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

		logger.warn("通过spring上下文对象发布监听 -> MyEvent " + msg);
		applicationContext.publishEvent(new MyEvent(this, msg));

	}
}