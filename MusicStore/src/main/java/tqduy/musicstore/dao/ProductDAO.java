package tqduy.musicstore.dao;

import tqduy.musicstore.entity.Product;

public interface ProductDAO {
	public void addProduct(Product product);
	public void editProduct(Product product);
	public void deleteProduct(Product product);
}
