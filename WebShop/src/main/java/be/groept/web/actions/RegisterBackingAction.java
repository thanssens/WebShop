package be.groept.web.actions;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.facade.UserFacade;

/**
 * 
 * @author Tom Hanssens
 *
 */
@ManagedBean(name = "registerBackingAction")
@SessionScoped
@Controller
public class RegisterBackingAction {

	@Autowired
	private UserFacade userFacade;

	private String firstname;
	private String lastname;
	private String birthdate;
	private String phone;
	private String email;

	private String username;
	private String password;
	private Integer userrole;

	public String clear() {
		firstname = null;
		lastname = null;
		birthdate = null;
		phone = null;
		email = null;

		username = null;
		password = null;
		userrole = null;

		return "clear";
	}

	public void register() {
		if (userFacade.registerUser(firstname, lastname, birthdate, phone, email, username, password, userrole)) {
			clear();
		} else {
			FacesMessage message = new FacesMessage("The username '" + username + "' already exists.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getUserrole() {
		return userrole;
	}

	public void setUserrole(Integer userrole) {
		this.userrole = userrole;
	}

}