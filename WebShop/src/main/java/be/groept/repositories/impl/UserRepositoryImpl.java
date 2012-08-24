package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import be.groept.repositories.UserRepository;
import be.groept.repositories.entities.user.UserEntity;
import be.groept.repositories.entities.user.UserInfoEntity;
import be.groept.repositories.entities.user.UserRoleEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class UserRepositoryImpl extends HibernateTemplate implements UserRepository {

	public UserRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfoEntity> searchUserInfo(String username) {
		return find("select uie from UserInfoEntity uie where uie.username = ?", username);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleEntity> searchUserRole(String username) {
		return find("select ure from UserRoleEntity ure where ure.username = ?", username);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> searchUser(String username) {
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