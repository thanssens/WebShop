package be.groept.repositories;

import java.util.List;

import be.groept.repositories.entities.user.UserEntity;
import be.groept.repositories.entities.user.UserInfoEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface UserRepository {

	UserEntity createUser(String username, String password, int userrole);
	UserInfoEntity createUserInfo(String username, String firstname, String lastname, String birthdate, String phone, String email);

	List<UserEntity> findUser(String username);
	//List<UserRoleEntity> findUserRole(String username);
	//List<UserEntity> findUserName(String username);

	void saveUser(UserEntity userEntity);
	void saveUserInfo(UserInfoEntity userInfoEntity);

}