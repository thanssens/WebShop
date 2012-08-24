package be.groept.support;

import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import be.groept.repositories.entities.SomeEntity;
import be.groept.repositories.entities.product.ProductCategoryEntity;
import be.groept.repositories.entities.product.ProductEntity;
import be.groept.repositories.entities.user.UserEntity;
import be.groept.repositories.entities.user.UserInfoEntity;
import be.groept.repositories.entities.user.UserRoleEntity.Role;

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
		userInfoEntity1.setFirstname("First1");
		userInfoEntity1.setLastname("Last1");
		userInfoEntity1.setBirthdate("08-08-1988");
		userInfoEntity1.setPhone("0488224466");
		userInfoEntity1.setEmail("user1@example.com");

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setUsername("super");
		userEntity2.setPassword("super");
		userEntity2.addUserRole(Role.Super);

		UserInfoEntity userInfoEntity2 = new UserInfoEntity();
		userInfoEntity2.setUser(userEntity2);
		userInfoEntity2.setFirstname("First2");
		userInfoEntity2.setLastname("Last2");
		userInfoEntity2.setBirthdate("08-08-1988");
		userInfoEntity2.setPhone("0488224466");
		userInfoEntity2.setEmail("user2@example.com");

		ProductCategoryEntity productCategoryEntity1 = new ProductCategoryEntity();
		productCategoryEntity1.setName("Household");
		ProductCategoryEntity productCategoryEntity2 = new ProductCategoryEntity();
		productCategoryEntity2.setName("Electronics");
		ProductCategoryEntity productCategoryEntity3 = new ProductCategoryEntity();
		productCategoryEntity3.setName("Hifi");

		ProductEntity productEntity1 = new ProductEntity();
		productEntity1.setName("Product1");
		productEntity1.setCategoryName(productCategoryEntity1);
		productEntity1.setPrice(1000);

		ProductEntity productEntity2 = new ProductEntity();
		productEntity2.setName("Product2");
		productEntity2.setCategoryName(productCategoryEntity2);
		productEntity2.setPrice(100);

		ProductEntity productEntity3 = new ProductEntity();
		productEntity3.setName("Product3");
		productEntity3.setCategoryName(productCategoryEntity3);
		productEntity3.setPrice(200);
/*
		StockEntity stockEntity1 = new StockEntity();
		stockEntity1.setProductEntity(productEntity1);
		stockEntity1.setStock(50);

		StockEntity stockEntity2 = new StockEntity();
		stockEntity2.setProductEntity(productEntity2);
		stockEntity2.setStock(100);

		StockEntity stockEntity3 = new StockEntity();
		stockEntity3.setProductEntity(productEntity3);
		stockEntity3.setStock(1000);
*/
		session.save(someEntity);
		session.save(userEntity1);
		session.save(userEntity2);
		session.save(userInfoEntity1);
		session.save(userInfoEntity2);
		session.save(productCategoryEntity1);
		session.save(productCategoryEntity2);
		session.save(productCategoryEntity3);
		session.save(productEntity1);
		session.save(productEntity2);
		session.save(productEntity3);
		/*session.save(stockEntity1);
		session.save(stockEntity2);
		session.save(stockEntity3);*/
		platformTransactionManager.commit(transactionStatus);
	}

}