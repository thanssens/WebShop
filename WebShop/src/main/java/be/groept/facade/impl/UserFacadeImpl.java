package be.groept.facade.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import be.groept.facade.UserFacade;
import be.groept.repositories.UserRepository;
import be.groept.repositories.entities.user.UserEntity;
import be.groept.repositories.entities.user.UserInfoEntity;
import be.groept.repositories.entities.user.UserRoleEntity;

public class UserFacadeImpl implements UserFacade {

	private final UserRepository userRepository;

	public UserFacadeImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String getUserName(String username) {
		String userName = "";
		List<UserInfoEntity> results = userRepository.searchUserInfo(username);

		if(results.size() == 1) {
			UserInfoEntity userInfoEntity = results.get(0);
			userName += userInfoEntity.getFirstname() + " " + userInfoEntity.getLastname();
		}

		return userName;
	}

	@Override
	public String getUserRole(String username) {
		String userRole = "";
		List<UserRoleEntity> results = userRepository.searchUserRole(username);

		UserRoleEntity userRoleEntity = results.get(results.size()-1);
		userRole = userRoleEntity.getRolename();

		return userRole;
	}

	@Transactional
	@Override
	public boolean registerUser(String firstname, String lastname, String birthdate, String phone, String email, String username, String password, int userrole) {
		if (userRepository.searchUser(username).isEmpty()) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUsername(username);
			userEntity.setPassword(password);
			userEntity.setRoleValue(userrole);

			if (userRepository.searchUserInfo(username).isEmpty()) {
				UserInfoEntity userInfoEntity = new UserInfoEntity();
				userInfoEntity.setUser(userEntity);
				userInfoEntity.setFirstname(firstname);
				userInfoEntity.setLastname(lastname);
				userInfoEntity.setBirthdate(birthdate);
				userInfoEntity.setPhone(phone);
				userInfoEntity.setEmail(email);

				userRepository.saveUser(userEntity);
				userRepository.saveUserInfo(userInfoEntity);

				return true;
			}
		}

		return false;
	}

}