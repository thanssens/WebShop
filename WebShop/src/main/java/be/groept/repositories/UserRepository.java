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

	List<UserEntity> searchUser(String username);
	List<UserInfoEntity> searchUserInfo(String username);
	List<UserRoleEntity> searchUserRole(String username);

	void saveUser(UserEntity userEntity);
	void saveUserInfo(UserInfoEntity userInfoEntity);

}