package be.groept.repositories.entities;

import java.util.Collection;
import java.util.LinkedList;
import be.groept.repositories.entities.UserRoleEntity.Role;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class UserEntity {

	private Long id;

	//Always unique key generated
	//private String failSafeId = UUID.randomUUID().toString();

	private String username;
	private String password;
	private Collection<UserRoleEntity> userRoles = new LinkedList<UserRoleEntity>();

	//Iterable cannot be changed
	public Iterable<Role> getUserRoles() {
		Collection<Role> roles = new LinkedList<Role>();
		for (UserRoleEntity userRoleEntity : getRoles()) {
			roles.add(userRoleEntity.getRole());
		}
		return roles;
	}

	public void addUserRole(Role role) {
		if (role.equals(Role.Super)) {
			addUserRole(Role.Registered);
		}

		for (UserRoleEntity userRoleEntity : getRoles()) {
			if (userRoleEntity.getRole().equals(role)) {
				return;
			}
		}

		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleEntity.setUser(this);
		userRoleEntity.setRole(role);
		getRoles().add(userRoleEntity);
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

	protected Collection<UserRoleEntity> getRoles() {
		return userRoles;
	}

}