package com.tommy.springboot.golbal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e)
		throws Exception {

		e.printStackTrace();

		if (isAjax(reqest)) {
			return "{json:errot}";
		} else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("config", e);
			mav.addObject("url", reqest.getRequestURL());
			mav.setViewName(ERROR_VIEW);
			return mav;
		}
	}


	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
			&& "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
	}
}
