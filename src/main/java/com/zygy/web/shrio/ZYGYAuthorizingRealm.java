package com.zygy.web.shrio;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zygy.web.po.UserPo;
import com.zygy.web.service.UserService;

public class ZYGYAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		//���ý�ɫȨ��
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		
		//�˴���֤��֤��
		
		//����У������֤ͨ���Ժ�,�����ݿ�  
        String username = (String) token.getPrincipal();  
        char[] password = token.getPassword();
        UserPo user = userService.findUserByUsername(username);
		
      //����AuthenticatingRealmʹ��CredentialsMatcher��������ƥ�䣬��������˼ҵĲ��ÿ����Զ���ʵ��  
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
                user.getUserName(), //�û�
                user.getPassword(), //����  
                this.getName()  //realm name  
        );
		return authenticationInfo;
	}

}
