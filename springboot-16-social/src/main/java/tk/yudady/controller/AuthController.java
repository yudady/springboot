package tk.yudady.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;
import tk.yudady.service.UserService;
import tk.yudady.support.SimpleResponse;

@Controller
public class AuthController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RequestCache requestCache;

	@Autowired
	UserService userService;

	@ApiOperation("處理json，authentication")
	@RequestMapping(name = "/authentication/request", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SimpleResponse registrationAjax(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		logger.info("/authentication/request");
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		logger.info("savedRequest.RedirectUrl=> {} " + savedRequest.getRedirectUrl());
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是:" + targetUrl);
		}
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
	}

	@ApiOperation("處理html，authentication")
	@RequestMapping(name = "/authentication/request")
	public ModelAndView registrationHtml(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		logger.info("/authentication/request");
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		logger.info("savedRequest.RedirectUrl=> {} " + savedRequest.getRedirectUrl());
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是:" + targetUrl);
		}

		return new ModelAndView("/login");
	}

	@ApiOperation("處理登入成功，authentication")
	@PostMapping({"/redirectHome"})
	public String redirectHome() {
		logger.info("登入成功");
		return "redirect:/home.html";

	}


//	@ApiOperation("處理登出成功，authentication")
//	@PostMapping({"/logout/index"})
//	public String logout() {
//		logger.info("登出成功");
//		return "redirect:/logout/success";
//
//	}
//
//	@ApiOperation("處理json，登出")
//	@RequestMapping(name = "/logout/success", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public SimpleResponse logoutAjax(HttpServletRequest request, HttpServletResponse response)
//		throws IOException {
//		logger.info("/logout/index");
//		SavedRequest savedRequest = requestCache.getRequest(request, response);
//		logger.info("savedRequest.RedirectUrl=> {} " + savedRequest.getRedirectUrl());
//		if (savedRequest != null) {
//			String targetUrl = savedRequest.getRedirectUrl();
//			logger.info("引发跳转的请求是:" + targetUrl);
//		}
//		return new SimpleResponse("用戶登出");
//	}
//
//	@ApiOperation("處理html，登出")
//	@RequestMapping(name = "/logout/success")
//	public ModelAndView logoutHtml(HttpServletRequest request, HttpServletResponse response)
//		throws IOException {
//		logger.info("/logout/index");
//		SavedRequest savedRequest = requestCache.getRequest(request, response);
//		logger.info("savedRequest.RedirectUrl=> {} " + savedRequest.getRedirectUrl());
//		if (savedRequest != null) {
//			String targetUrl = savedRequest.getRedirectUrl();
//			logger.info("用戶登出，引发跳转的请求是:" + targetUrl);
//		}
//		return new ModelAndView("/logout");
//	}

}
