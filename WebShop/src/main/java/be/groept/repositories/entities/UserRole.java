package be.groept.repositories.entities;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class UserRole {

	private Long id;

	private Long userId;

	private String rolename;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}