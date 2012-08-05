package be.groept.web.actions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.facade.MyFacade;

@ManagedBean(name = "searchScreenBackingAction")
@SessionScoped
@Controller
public class SearchScreenBackingAction {

	@Autowired
	private MyFacade myFacade;

	private String firstName;
	private String lastName;

	private String age;

	public void calculateAge() {
		age = "" + myFacade.calculateAgeInYears(firstName, lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
