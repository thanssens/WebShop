package be.groept.facade;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface UserFacade {

	String getUserName(String username);
	String getUserRole(String username);

	boolean registerUser(String firstname, String lastname, String birthdate, String phone, String email, String username, String password, int userrole);

}