package com.dragonsoft.cube.module.admin.serivce;

import com.dragonsoft.dids.client.sys.DidsServiceLocator;
import com.dragonsoft.dids.model.AuthResult;
import com.dragonsoft.dids.model.BaseUserInfo;
import com.dragonsoft.dids.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpSession;

/**
 * Created: 2016/9/5.
 * Author: Qiannan Lu
 */
@Service
public class LoginService {
	public void login(HttpSession session, String remoteAddress) {
		AuthResult authResult = DidsServiceLocator.getUserResourceService(session).getAuthResult();
		Assert.notNull(authResult, "DIDS 授权用户不能为空");
		UserInfo didsUser = DidsServiceLocator.getUserResourceService().getUserInfo(authResult.getUserId());
		BaseUserInfo baseDidsUser = didsUser.getBaseUserInfo();
		baseDidsUser.setAddress(remoteAddress);



	}
}
