package tqduy.musicstore.utils;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import tqduy.musicstore.entity.Product;
import tqduy.musicstore.entity.Account;

public class HiberneteUtils {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory factory = HiberneteUtils.getSessionFactory();
	// Hibernate 5:
	private static SessionFactory buildSessionFactory() {
		try {
			// Tạo đối tượng ServiceRegistry
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
					.configure("hibernate.cfg.xml").build();

			// Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

			return metadata.getSessionFactoryBuilder().build();
		} catch (Throwable ex) {

			System.out.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Giải phóng cache và Connection Pools.
		getSessionFactory().close();
	}

	public static List<Product> getListProduct() {

		Session session = factory.getCurrentSession();
		List<Product> products = null;

		try {
			session.getTransaction().begin();

			String sql = "from " + Product.class.getName() + " p ";

			Query<Product> query = session.createQuery(sql);

			products = query.getResultList();
			for (Product product : products) {
				System.out.println("Name: " + product.getName() + " - Price: " + product.getPrice());
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		return products;
	}
	
	public static List<Product> findProduct(String code) {

		Session session = factory.getCurrentSession();
		List<Product> products = null;

		try {
			session.getTransaction().begin();

			String sql = "from " + Product.class.getName() + " p where p.code = '"+code+"'";

			Query<Product> query = session.createQuery(sql);

			products = query.getResultList();
			for (Product product : products) {
				System.out.println("Name: " + product.getName() + " - Price: " + product.getPrice());
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		return products;
	}
	
	public static boolean checkUser(Account user) throws SQLException {

		Session session = factory.getCurrentSession();
		
		try {
			session.getTransaction().begin();

			String sql = "FROM "+Account.class.getName()+"";

			Query query = session.createQuery(sql);
			
			List<Account> userNames = query.list();
			System.out.println(userNames.isEmpty());
			for (Account userName : userNames) {
//				if(userName.getUserName().equals(user.getUserName()) && userName.getPassWord().equals(user.getPassWord())) {
//					return true;
//				}
				System.out.println("UserName: " + userName.getUserName() + " - Pass: " + userName.getPassWord());
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		
		return false;
	}
	
	public static Account selectUser(String userName) {
		Session session = factory.getCurrentSession();
		String hql = "From Account where userName like '"+userName+"'";
		Query query = session.createQuery(hql);
		Account account =(Account) query.uniqueResult();
		return account;
	}
	
	public static void insertMusic(Product product) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(product);
			t.commit();
			System.out.println("Success !!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			t.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void editMusic(Product product) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(product);
			t.commit();
			System.out.println("Success !!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			t.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void main(String[] args) {
		getListProduct();
	}
	
	public static void deleteMusic(Product product) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(product);
			
			t.commit();
			System.out.println("Success !!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			t.rollback();
		} finally {
			session.close();
		}
	}
}
