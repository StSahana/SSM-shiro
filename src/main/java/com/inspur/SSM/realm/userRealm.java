package com.inspur.SSM.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class userRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addRole("user");
		info.addStringPermission("user:test");
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken token=(UsernamePasswordToken)arg0;
		String username =token.getUsername();
		String password=String.valueOf(token.getPassword());
		if(password.equals("abc123")){
			return new SimpleAuthenticationInfo(username,password,this.getName());
		}else{
			return null;
		}
	}

}
