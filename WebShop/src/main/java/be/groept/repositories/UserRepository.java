package be.groept.repositories;

import java.util.List;

import be.groept.repositories.entities.user.UserEntity;
import be.groept.repositories.entities.user.UserInfoEntity;
import be.groept.repositories.entities.user.UserRoleEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface UserRepository {

	List<UserInfoEntity> searchUserInfo(String username);
	List<UserRoleEntity> searchUserRole(String username);

	UserEntity createUser(String username, String password, int userrole);
	UserInfoEntity createUserInfo(String username, String firstname, String lastname, String birthdate, String phone, String email);

	//List<UserEntity> findUser(String username);
	//List<UserRoleEntity> findUserRole(String username);
	//

	void saveUser(UserEntity userEntity);
	void saveUserInfo(UserInfoEntity userInfoEntity);

}