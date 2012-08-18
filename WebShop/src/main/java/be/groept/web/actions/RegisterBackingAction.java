package be.groept.web.actions;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.facade.UserFacade;
import be.groept.repositories.entities.UserEntity;
import be.groept.repositories.entities.UserInfoEntity;

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

	private UserEntity userEntity = new UserEntity();
	private UserInfoEntity userInfoEntity = new UserInfoEntity();

	private String userrole;

	public String clear() {
		userEntity = new UserEntity();
		userInfoEntity = new UserInfoEntity();
		userrole = null;

		return "clear";
	}

	public void register() {
		if ((userFacade.registerUser(userInfoEntity, userEntity, userrole))) {
			clear();
		} else {
			FacesMessage message = new FacesMessage("The username '" + userEntity.getUsername() + "' already exists.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public UserInfoEntity getUserInfoEntity() {
		return userInfoEntity;
	}

	public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
		this.userInfoEntity = userInfoEntity;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

}