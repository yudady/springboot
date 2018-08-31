package tk.yudady.mvc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class TestListener implements ServletContextListener {
 
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("listener destroy");
	}
 
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("listener init");
	}
 
}