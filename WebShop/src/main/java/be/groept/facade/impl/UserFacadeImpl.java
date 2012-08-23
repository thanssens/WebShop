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

	@Override
	public String getUserMasterRole(String username) {
		List<UserEntity> users = userRepository.findUser(username);
		if (users.size() == 1) {
			return users.get(0).getMasterRole();
		}

		return UserRoleEntity.Role.Registered.toString();
	}

}