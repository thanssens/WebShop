package be.groept.repositories.entities.user;

import java.util.Collection;
import java.util.LinkedList;

import be.groept.repositories.entities.user.UserRoleEntity.Role;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class UserEntity {

	//Always unique key generated
	//private String failSafeId = UUID.randomUUID().toString();

	private Long id;

	private String username;
	private String password;

	private String masterRole;

	private Collection<UserRoleEntity> userRoles = new LinkedList<UserRoleEntity>();

	//Iterable cannot be changed
	public Iterable<Role> getRoles() {
		Collection<Role> roles = new LinkedList<Role>();
		for (UserRoleEntity userRoleEntity : getUserRoles()) {
			roles.add(userRoleEntity.getRole());
		}
		return roles;
	}

	public void addUserRole(Role role) {
		if (role.equals(Role.Super)) {
			addUserRole(Role.Registered);
		}

		for (UserRoleEntity userRoleEntity : getUserRoles()) {
			if (userRoleEntity.getRole().equals(role)) {
				return;
			}
		}

		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleEntity.setUser(this);
		userRoleEntity.setRole(role);
		getUserRoles().add(userRoleEntity);
		setMasterRole(userRoleEntity.getRolename());
	}

	public void setRoleValue(int roleValue) {
		switch (roleValue) {
		case 0:
			addUserRole(Role.Registered);
			break;
		case 1:
			addUserRole(Role.Super);
			break;
		default:
			break;
		}
	}

	public String getMasterRole() {
		return masterRole;
	}

	public void setMasterRole(String masterRole) {
		this.masterRole = masterRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Collection<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

}