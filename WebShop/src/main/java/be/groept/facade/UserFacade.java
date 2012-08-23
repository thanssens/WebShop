package be.groept.facade;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface UserFacade {

	boolean registerUser(String firstname, String lastname, String birthdate, String phone, String email, String username, String password, int userrole);

	String getUserMasterRole(String username);

}