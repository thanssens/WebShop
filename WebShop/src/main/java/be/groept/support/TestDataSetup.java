package be.groept.support;

import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import be.groept.repositories.entities.SomeEntity;

public class TestDataSetup {

	private SessionFactory sessionFactory;
	private PlatformTransactionManager platformTransactionManager;

	public TestDataSetup(SessionFactory sessionFactory, PlatformTransactionManager platformTransactionManager) {
		this.sessionFactory = sessionFactory;
		this.platformTransactionManager = platformTransactionManager;
	}

	public void setup() {
		TransactionStatus transactionStatus = platformTransactionManager
				.getTransaction(new DefaultTransactionDefinition());

		Session session = sessionFactory.getCurrentSession();

		SomeEntity someEntity = new SomeEntity();
		someEntity.setFirstName("John");
		someEntity.setLastName("Doe");
		someEntity.setBirthDate(new GregorianCalendar(2000, 1, 1).getTime());

		session.save(someEntity);
		platformTransactionManager.commit(transactionStatus);
	}

}
