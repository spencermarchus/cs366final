package edu.ndsu.finalProject.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

public class GuardianLogin
{
  @Inject
  private Logger logger;

  @Inject
  private AlertManager alertManager;

  @InjectComponent
  private Form login;
  
  @InjectComponent("email")
  private TextField emailField;
  
  @InjectComponent("password")
  private PasswordField passwordField;

  @Property
  private String email;

  @Property
  private String password;



  void onValidateFromLogin()
  {
	  try {
		  UsernamePasswordToken token = new UsernamePasswordToken(email, password);
		  Subject currentUser = SecurityUtils.getSubject();
		  currentUser.login(token);	  
	  }catch(Exception e)
	  {
		  login.recordError("User/password combination does not match");
	  }
  }

  Object onSuccessFromLogin()
  {
    logger.info("Login successful!");
    alertManager.success("Welcome aboard!");

    return Index.class;
  }

  void onFailureFromLogin()
  {
    logger.warn("Login error!");
    alertManager.error("I'm sorry but I can't log you in!");
  }

}
