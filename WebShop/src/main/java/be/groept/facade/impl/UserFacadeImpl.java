package be.groept.facade.impl;

import java.util.List;

import be.groept.facade.UserFacade;
import be.groept.repositories.UserRepository;
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
/*
	@Transactional
	@Override
	public boolean registerUser(String firstname, String lastname, String birthdate, String phone, String email, String username, String password, int userrole) {
		List<UserEntity> users = userRepository.findUser(username);
		if (users.isEmpty()) {
			UserEntity user = userRepository.createUser(username, password, userrole);
			UserInfoEntity userInfo = userRepository.createUserInfo(username, firstname, lastname, birthdate, phone, email);
			userRepository.saveUser(user);
			userRepository.saveUserInfo(userInfo);

			return true;
		}

		return false;
	}
*/
}