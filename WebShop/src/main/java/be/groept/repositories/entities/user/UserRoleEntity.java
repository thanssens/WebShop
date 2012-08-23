package be.groept.repositories.entities.user;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class UserRoleEntity {

	private Long id;

	private String username;
	private String rolename;

	public enum Role {
		Registered,
		Super
	}

	public Role getRole() {
		return Role.valueOf(rolename);
	}

	public void setRole(Role rolename) {
		this.rolename = rolename.toString();
	}

	public void setUser(UserEntity userEntity) {
		this.username = userEntity.getUsername();
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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}