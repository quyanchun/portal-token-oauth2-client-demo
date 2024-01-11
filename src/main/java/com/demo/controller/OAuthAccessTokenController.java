package com.demo.controller;

import com.mingxing.config.PortalTokenOauth2Config;
import com.mingxing.domain.TResult;
import com.mingxing.utils.Oauth2TokenUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Client端
 */
@RestController
public class OAuthAccessTokenController {

	@Resource
	private PortalTokenOauth2Config portalTokenOauth2Config;

	// 进入首页 
	@RequestMapping("/")
	public Object index(HttpServletRequest request) {
		request.setAttribute("serverHost", portalTokenOauth2Config.getServerHost());
		request.setAttribute("clientId", portalTokenOauth2Config.getClientId());
		return new ModelAndView("index");
	}


	// 根据Code码进行登录，获取 Access-Token 和 openid  
	@RequestMapping("/codeLogin")
	public TResult codeLogin(String code) {
		// 调用Server端接口，获取 Access-Token 以及其他信息
		System.out.println(code);
 		return TResult.ok(Oauth2TokenUtil.tokenByToken(code));
	}


	// 根据账号密码进行登录，获取 Access-Token 和 openid
	@RequestMapping("/passwordLogin")
	public TResult passwordLogin(String username, String password) {
		return TResult.ok(Oauth2TokenUtil.tokenByPassword(username,password));
	}

	// 根据 Refresh-Token 去刷新 Access-Token
	@RequestMapping("/refresh")
	public TResult refresh(String refreshToken) {
		return TResult.ok(Oauth2TokenUtil.refresh(refreshToken));
	}

	// 根据 Access-Token 置换相关的资源: 获取账号昵称、头像、性别等信息
	@RequestMapping("/getUserinfo")
	public TResult getUserinfo(String accessToken) {
		return TResult.ok(Oauth2TokenUtil.userinfo(accessToken));
	}

	// 注销登录
	@RequestMapping("/logout")
	public TResult logout(String accessToken) {
		return Oauth2TokenUtil.logout(accessToken);
	}

}
