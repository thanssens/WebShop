package be.groept.support;

import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import be.groept.repositories.entities.SomeEntity;
import be.groept.repositories.entities.UserEntity;
import be.groept.repositories.entities.UserInfoEntity;
import be.groept.repositories.entities.UserRoleEntity.Role;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class TestDataSetup {

	private SessionFactory sessionFactory;
	private PlatformTransactionManager platformTransactionManager;

	public TestDataSetup(SessionFactory sessionFactory, PlatformTransactionManager platformTransactionManager) {
		this.sessionFactory = sessionFactory;
		this.platformTransactionManager = platformTransactionManager;
	}

	public void setup() {
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

		Session session = sessionFactory.getCurrentSession();

		SomeEntity someEntity = new SomeEntity();
		someEntity.setFirstName("John");
		someEntity.setLastName("Doe");
		someEntity.setBirthDate(new GregorianCalendar(2000, 1, 1).getTime());

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setUsername("regged");
		userEntity1.setPassword("regged");
		userEntity1.addUserRole(Role.Registered);

		UserInfoEntity userInfoEntity1 = new UserInfoEntity();
		userInfoEntity1.setUser(userEntity1);
		userInfoEntity1.setFirstname("firstname");
		userInfoEntity1.setLastname("lastname");
		userInfoEntity1.setBirthdate("08-08-1988");
		userInfoEntity1.setPhone("0488224466");
		userInfoEntity1.setEmail("user1@example.com");

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setUsername("super");
		userEntity2.setPassword("super");
		userEntity2.addUserRole(Role.Super);

		UserInfoEntity userInfoEntity2 = new UserInfoEntity();
		userInfoEntity2.setUser(userEntity2);
		userInfoEntity2.setFirstname("firstname");
		userInfoEntity2.setLastname("lastname");
		userInfoEntity2.setBirthdate("08-08-1988");
		userInfoEntity2.setPhone("0488224466");
		userInfoEntity2.setEmail("user1@example.com");

		session.save(someEntity);
		session.save(userEntity1);
		session.save(userEntity2);
		session.save(userInfoEntity1);
		session.save(userInfoEntity2);
		platformTransactionManager.commit(transactionStatus);
	}

}