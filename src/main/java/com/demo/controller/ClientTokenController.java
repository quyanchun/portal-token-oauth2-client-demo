package com.demo.controller;

import com.mingxing.domain.TResult;
import com.mingxing.domain.TTableResult;
import com.mingxing.domain.oauth2.PortalDept;
import com.mingxing.domain.oauth2.PortalUser;
import com.mingxing.domain.oauth2.PortalUserResetPwd;
import com.mingxing.enums.PortalDeptType;
import com.mingxing.utils.ClientTokenUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Client端
 */
@RestController
public class ClientTokenController {


	// 获取应用的 Client-Token
	@RequestMapping("/clientToken")
	public TResult clientToken() {
		return TResult.ok(ClientTokenUtil.clientToken());
	}

	// 获取应用的部门信息
	@RequestMapping("/deptInfo")
	public TTableResult<PortalDept> deptInfo(String clientToken) {
		return ClientTokenUtil.deptInfo(clientToken, PortalDeptType.list);
	}

	// 获取应用的用户列表
	@RequestMapping("/userList")
	public TTableResult<PortalUser> userList(String clientToken) {
		return ClientTokenUtil.userList(clientToken, null, null, null, null, 1, 10);
	}

	// 重置密码
	@PostMapping("/resetPwd/{clientToken}")
	public TResult userList(PortalUserResetPwd portalUserResetPwd, @PathVariable String clientToken) {
		return ClientTokenUtil.resetPassword(clientToken,portalUserResetPwd.getUserId(),portalUserResetPwd.getPassword());
	}

}
