package be.groept.web.actions;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.facade.UserFacade;

/**
 * 
 * @author Tom Hanssens
 *
 */
@ManagedBean(name = "loginBackingAction")
@SessionScoped
@Controller
public class LoginBackingAction {

	@Autowired
	private UserFacade userFacade;

	private String username;
	private String password;

	private boolean loggedIn;
	private String user;
	private String role;

	public String logon() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		try {
			request.login(username, password);
			loggedIn = true;
			user = userFacade.getUserName(username);
			role = userFacade.getUserRole(username);
			//FacesContext.getCurrentInstance().getExternalContext().redirect(((String) request.getSession().getAttribute("from")));
		} catch (ServletException e) {
			FacesMessage message = new FacesMessage("The entered username or password is not correct.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);

			loggedIn = false;
			user = "";
			role = "";
			return "error";
		}

		return "success";
	}

	public String logoff() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		try {
			request.logout();
		} catch (ServletException e) {
			loggedIn = true;
			user = userFacade.getUserName(username);
			role = userFacade.getUserRole(username);
			return "error";
		}

		loggedIn = false;
		user = "";
		role = "";
		return "success";
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

	public boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}