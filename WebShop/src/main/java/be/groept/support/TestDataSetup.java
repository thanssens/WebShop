package be.groept.support;

import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import be.groept.repositories.entities.ProductCategoryEntity;
import be.groept.repositories.entities.ProductEntity;
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

		ProductCategoryEntity productCategoryEntity1 = new ProductCategoryEntity();
		productCategoryEntity1.setName("Household");
		ProductCategoryEntity productCategoryEntity2 = new ProductCategoryEntity();
		productCategoryEntity2.setName("Electronics");
		ProductCategoryEntity productCategoryEntity3 = new ProductCategoryEntity();
		productCategoryEntity3.setName("Hifi");



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

		ProductEntity productEntity1 = new ProductEntity();
		productEntity1.setName("Product1");
		productEntity1.setCategory("Household");
		productEntity1.setPrice(1000);
		productEntity1.setStock(50);

		ProductEntity productEntity2 = new ProductEntity();
		productEntity2.setName("Product2");
		productEntity2.setCategory("Electronics");
		productEntity2.setPrice(100);
		productEntity2.setStock(100);

		ProductEntity productEntity3 = new ProductEntity();
		productEntity3.setName("Product3");
		productEntity3.setCategory("Hifi");
		productEntity3.setPrice(200);
		productEntity3.setStock(1000);

		session.save(someEntity);
		session.save(userEntity1);
		session.save(userEntity2);
		session.save(userInfoEntity1);
		session.save(userInfoEntity2);
		session.save(productEntity1);
		session.save(productEntity2);
		session.save(productEntity3);
		platformTransactionManager.commit(transactionStatus);
	}

}