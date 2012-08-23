package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import be.groept.repositories.UserRepository;
import be.groept.repositories.entities.user.UserEntity;
import be.groept.repositories.entities.user.UserInfoEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class UserRepositoryImpl extends HibernateTemplate implements UserRepository {

	public UserRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public UserEntity createUser(String username, String password, int userrole) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userEntity.setRoleValue(userrole);

		return userEntity;
	}

	@Override
	public UserInfoEntity createUserInfo(String username, String firstname, String lastname, String birthdate, String phone, String email) {
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		userInfoEntity.setUsername(username);
		userInfoEntity.setFirstname(firstname);
		userInfoEntity.setLastname(lastname);
		userInfoEntity.setBirthdate(birthdate);
		userInfoEntity.setPhone(phone);
		userInfoEntity.setEmail(email);

		return userInfoEntity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findUser(String username) {
		return (List<UserEntity>) find("select ue from UserEntity ue where ue.username = ?", username);
	}

	@Override
	public void saveUser(UserEntity userEntity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(userEntity);
	}

	@Override
	public void saveUserInfo(UserInfoEntity userInfoEntity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(userInfoEntity);
	}

}