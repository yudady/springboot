package tk.yudady.mvc;

import java.io.IOException;

import javax.servlet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

		filterRegistrationBean.setFilter(new JavaEE());
		filterRegistrationBean.addInitParameter("key", "value");
		filterRegistrationBean.addUrlPatterns(new String[]{"/*"});

		return filterRegistrationBean;
	}

	public class JavaEE implements Filter {

		protected final Logger logger = LoggerFactory.getLogger(this.getClass());

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {

		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			logger.info("JavaEE.start");
			chain.doFilter(request, response);
			logger.info("JavaEE.finish");
		}

		@Override
		public void destroy() {

		}
	}

}
