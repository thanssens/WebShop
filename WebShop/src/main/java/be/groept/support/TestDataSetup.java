package be.groept.support;

import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import be.groept.repositories.entities.SomeEntity;
import be.groept.repositories.entities.UserEntity;
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

		UserEntity userEntity = new UserEntity();
		userEntity.setUsername("user");
		userEntity.setPassword("pass");
		userEntity.addUserRole(Role.Super);

		session.save(someEntity);
		session.save(userEntity);
		platformTransactionManager.commit(transactionStatus);
	}

}