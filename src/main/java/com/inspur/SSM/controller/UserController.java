package com.inspur.SSM.controller;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class UserController {
	Logger log=Logger.getLogger(UserController.class);
	private String username;
	private String password;

	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(){
		return "list";
	}
	
	
	@RequestMapping(value="/test01",method=RequestMethod.GET)
	@RequiresPermissions("user:test")
	@RequiresRoles("user")
	public String test01(){
		return "test01";
	}
	
	
	@RequestMapping(value="/authentication",method=RequestMethod.POST)
	public String login(@RequestParam String username,@RequestParam String password){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		try {
            subject.login(token);
            return "redirect:/";
        } catch (UnknownAccountException uae) {
            log.info("Unknown User!");
        } catch (IncorrectCredentialsException ice) {
            log.info("Incorrect Password!");
        } catch (LockedAccountException lae) {
            log.info("User Locked!");
        } catch (AuthenticationException ae) {
            log.info("Authentication Failed!");
        }
        return "login";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
