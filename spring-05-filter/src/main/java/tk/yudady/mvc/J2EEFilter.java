package tk.yudady.mvc;

import java.io.IOException;

import javax.servlet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class J2EEFilter implements Filter {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		logger.info("J2EEFilter.start");
		chain.doFilter(request, response);
		logger.info("J2EEFilter.finish");
	}

	@Override
	public void destroy() {

	}
}
