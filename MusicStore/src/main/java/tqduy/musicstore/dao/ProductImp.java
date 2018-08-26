package tqduy.musicstore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tqduy.musicstore.entity.Product;
import tqduy.musicstore.utils.HiberneteUtils;

public class ProductImp implements ProductDAO {

	public void addProduct(Product product) {
		Session session = HiberneteUtils.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(product);
		transaction.commit();
	}
	
	public static void main(String[] args) {
		Product product = new Product();
		product.setCode(4);
		product.setName("Co duoc ko em");
		product.setPrice((float) 5.9);
		ProductDAO dao = new ProductImp();
		dao.addProduct(product);
	}

	public void editProduct(Product product) {
		// TODO Auto-generated method stub

	}

	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub

	}

}
