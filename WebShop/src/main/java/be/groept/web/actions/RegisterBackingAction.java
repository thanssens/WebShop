package be.groept.web.actions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

import be.groept.repositories.entities.UserEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
@ManagedBean(name = "registerBackingAction")
@SessionScoped
@Controller
public class RegisterBackingAction {

	private String firstname;
	private String lastname;
	private String birthdate;

	private String phone;
	private String email;

	private String username;
	private String password;
	private String userrole;

	public void Clear() {
		//Clear the fields
	}

	public void Register() {
		UserEntity userEntity = new UserEntity();
		
		//Validate and register new user
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

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

}